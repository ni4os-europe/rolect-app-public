package gr.uoa.madgik.rolect.pdf;


import com.lowagie.text.DocumentException;
import gr.uoa.madgik.rolect.model.response.FormResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;
import static org.thymeleaf.templatemode.TemplateMode.HTML;

@Service
public class PdfService {

    @Autowired
    TemplateEngine templateEngine;

    @PostConstruct
    public void templateInit(){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(HTML);

        templateEngine.setTemplateResolver(templateResolver);
    }

    public byte[] generatePdf(FormResponse data) throws DocumentException, IOException {

        // Parse and fill in html template
        Context context = new Context();


        context.setVariable("data",data);
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
}
