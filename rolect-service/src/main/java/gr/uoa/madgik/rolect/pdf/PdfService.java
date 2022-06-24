package gr.uoa.madgik.rolect.pdf;


import com.lowagie.text.DocumentException;
import gr.uoa.madgik.rolect.model.submitted_form.FormResponse;
import gr.uoa.madgik.rolect.security.UserPrincipal;
import gr.uoa.madgik.rolect.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.thymeleaf.templatemode.TemplateMode.HTML;

@Service
public class PdfService {

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    AuthService authService;

    @Value("${app.pdf.base-path}")
    private String reportsBaseDirectory;

    @PostConstruct
    public void templateInit(){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(HTML);

        templateEngine.setTemplateResolver(templateResolver);
    }

    public byte[] generatePdf(FormResponse data, Date date) throws DocumentException, IOException {

        // Parse and fill in html template
        Context context = new Context();

        UserPrincipal userPrincipal = authService.getPrincipal();


        context.setVariable("date",date);
        context.setVariable("data",data);
        context.setVariable("user",userPrincipal);
        System.out.println(data.checkObligatoryFields());
        context.setVariable("allObligatoryFieldsFilled",data.checkObligatoryFields());

        String renderedHtmlContent = templateEngine.process("report",context);

        // Render pdf from html template
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(renderedHtmlContent);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();

        return outputStream.toByteArray();
    }

    public String createTodayDirForUserIfNotExists(String username) {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Integer year  = localDate.getYear();
        Integer month = localDate.getMonthValue();
        Integer day   = localDate.getDayOfMonth();

        Path path = Paths.get(reportsBaseDirectory, year.toString(), month.toString(), day.toString(), username);
        new File(path.toString()).mkdirs();

        return Paths.get(year.toString(), month.toString(), day.toString(), username).toString();
    }

    public byte[] getReportPdf(String reportPath) throws IOException {
        return Files.readAllBytes(new File(reportsBaseDirectory + reportPath).toPath());

    }
}
