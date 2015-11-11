package com.example.junyeop_imaciislab.keeping.util;

/**
 * Created by LeeJunYeop on 2015-11-11.
 */
public class inventoryListDAO {
    private String cardNumber;
    private String name;
    private String id;
    private String birthDay;
    private String donationCategory;
    private String donationDate;
    private String gender;
    private String donationLocation;
    private String givingDate;
    private Boolean isGiven;
    private String message;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getDonationCategory() {
        return donationCategory;
    }

    public void setDonationCategory(String donationCategory) {
        this.donationCategory = donationCategory;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDonationLocation() {
        return donationLocation;
    }

    public void setDonationLocation(String donationLocation) {
        this.donationLocation = donationLocation;
    }

    public String getGivingDate() {
        return givingDate;
    }

    public void setGivingDate(String givingDate) {
        this.givingDate = givingDate;
    }

    public Boolean getIsGiven() {
        return isGiven;
    }

    public void setIsGiven(Boolean isGiven) {
        this.isGiven = isGiven;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
