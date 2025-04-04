package com.airbnb.service;

import com.airbnb.payload.BookingDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Service
public class PDFService {

    private final String FILE_DIRECTORY ="c://psap//pdfExample//";

    public boolean generatePDF(String fileName, BookingDto bookingDto){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(FILE_DIRECTORY+fileName+".pdf"));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        //Chunk chunk = new Chunk("Hello World", font);
        Chunk bookingConfirmation = new Chunk("Confirmation Status : "+" confirmed",font);
        Chunk name = new Chunk("name : " +bookingDto.getName(),font);
        Chunk email = new Chunk("email : " +bookingDto.getEmail(),font);
        Chunk totalNights = new Chunk( "number of nights : "+bookingDto.getTotalNights(),font);
        Chunk pricePerNight = new Chunk( "price per night : "+bookingDto.getPrice()/bookingDto.getTotalNights(),font);
        Chunk totalPrice = new Chunk("Total price : "+bookingDto.getPrice(),font);

        try {
            document.add(bookingConfirmation);
            document.add(new Paragraph("\n"));
            document.add(name);
            document.add(new Paragraph("\n"));
            document.add(email);
            document.add(new Paragraph("\n"));
            document.add(totalNights);
            document.add(new Paragraph("\n"));
            document.add(pricePerNight);
            document.add(new Paragraph("\n"));
            document.add(totalPrice);

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        document.close();
        return true;
    }
}
