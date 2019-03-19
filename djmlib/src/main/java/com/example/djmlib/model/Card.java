package com.example.djmlib.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IntDef;

import com.example.djmlib.database.SdkDb;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static com.example.djmlib.database.SdkDb.CARD_TB_NAME;
import static com.example.djmlib.database.SdkDb.COL_CARD_ID;
import static com.example.djmlib.database.SdkDb.COL_CARD_NUMBER;
import static com.example.djmlib.database.SdkDb.COL_CARD_MONTH;
import static com.example.djmlib.database.SdkDb.COL_CARD_YEAR;
import static com.example.djmlib.database.SdkDb.COL_CARD_TYPE;

public class Card {
    /**
     * IntDef : cardType must be either VISA or MASTERCARD
     */
    @IntDef({CardSchemes.CARD_TYPE_VISA, CardSchemes.CARD_TYPE_MASTERCARD})
    @Retention(RetentionPolicy.SOURCE)
    @interface CardSchemes {
        int CARD_TYPE_VISA = 0;
        int CARD_TYPE_MASTERCARD = 1;
    }

    private int cardId;
    private Long cardNumber;
    private int cardExpirationMonth;
    private int cardExpirationYear;
    private int cardType;

    Card(int cardId, Long cardNumber, int cardExpirationMonth, int cardExpirationYear, @CardSchemes int cardType) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.cardExpirationMonth = cardExpirationMonth;
        this.cardExpirationYear = cardExpirationYear;
        this.cardType = cardType;
    }

    public Card(Long cardNumber, int cardExpirationMonth, int cardExpirationYear, @CardSchemes int cardType) {
        this(0,cardNumber,cardExpirationMonth,cardExpirationYear,cardType);
    }

    public boolean saveCard(SdkDb sdb){
        SQLiteDatabase db = sdb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CARD_NUMBER, this.cardNumber);
        values.put(COL_CARD_MONTH, this.cardExpirationMonth);
        values.put(COL_CARD_YEAR, this.cardExpirationYear);
        values.put(COL_CARD_TYPE, this.cardType);

        return db.insert(CARD_TB_NAME, null, values) > 0;
    }

    public boolean deleteCard(SdkDb sdb){
        SQLiteDatabase db = sdb.getWritableDatabase();

        return db.delete(CARD_TB_NAME,
                "_id=?", new String[] { String.valueOf(this.cardId) }) == 1;
    }

    public static ArrayList<Card> getAllCards(SdkDb sdb){
        SQLiteDatabase db = sdb.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CARD_TB_NAME, new String[]{});
        ArrayList<Card> cards = new ArrayList<>();
        while(c.moveToNext()){
            cards.add(new Card(c.getInt(c.getColumnIndex(COL_CARD_ID)),
                               c.getLong(c.getColumnIndex(COL_CARD_NUMBER)),
                               c.getInt(c.getColumnIndex(COL_CARD_MONTH)),
                               c.getInt(c.getColumnIndex(COL_CARD_YEAR)),
                               c.getInt(c.getColumnIndex(COL_CARD_TYPE))));
        }
        c.close();
        return cards;
    }


    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", cardNumber=" + cardNumber +
                ", cardExpirationMonth=" + cardExpirationMonth +
                ", cardExpirationYear=" + cardExpirationYear +
                ", cardType=" + cardType +
                '}';
    }

    public int getCardId() {
        return cardId;
    }

    protected void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    protected void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardExpirationMonth() {
        return cardExpirationMonth;
    }

    protected void setCardExpirationMonth(int cardExpirationMonth) {
        this.cardExpirationMonth = cardExpirationMonth;
    }

    public int getCardExpirationYear() {
        return cardExpirationYear;
    }

    protected void setCardExpirationYear(int cardExpirationYear) {
        this.cardExpirationYear = cardExpirationYear;
    }

    public @CardSchemes int getCardType() {
        return cardType;
    }

    protected void setCardType(@CardSchemes int cardType) {
        this.cardType = cardType;
    }



}
