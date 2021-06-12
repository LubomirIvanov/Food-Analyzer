package decoder;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ImageDecoder {
    public final static String ERROR_MESSAGE = "Could not read the provided file";

    public String decodeImage(String path) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            return (ERROR_MESSAGE);
        }
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        Result result = null;
        try {
            result = new MultiFormatReader().decode(bitmap);
        } catch (NotFoundException e) {
            return (ERROR_MESSAGE);
        }
        return result.getText();
    }
}
