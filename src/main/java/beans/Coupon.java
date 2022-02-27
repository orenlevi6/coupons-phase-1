package beans;

import java.sql.Date;

public class Coupon {
    private int id;
    private int companyId;
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;

    private int categoryIndex;

    public Coupon(int id, int companyId, int categoryIndex, String title, String description,
                  Date startDate, Date endDate, int amount, double price, String image) {
        this.id = id;
        this.companyId = companyId;
        setCategory(Category.values()[categoryIndex - 1]);
        setTitle(title);
        setDescription(description);
        setStartDate(startDate);
        setEndDate(endDate);
        setAmount(amount);
        setPrice(price);
        setImage(image);
    }

    public Coupon(int companyId, int categoryIndex, String title, String description,
                  Date startDate, Date endDate, int amount, double price, String image) {
        this.id = 0;
        this.companyId = companyId;
        setCategory(Category.values()[categoryIndex]);
        setTitle(title);
        setDescription(description);
        setStartDate(startDate);
        setEndDate(endDate);
        setAmount(amount);
        setPrice(price);
        setImage(image);
    }

    public Coupon() {

    }

    public int getId() {
        return id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        for (Category item : Category.values()) {
            if (item.VALUE == category.VALUE) {
                this.category = category;
                return;
            }
        }
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if (amount < 0) {
            System.out.println("Invalid value, amount was set to 0");
            this.amount = 0;
            return;
        }
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            System.out.println("Invalid value, price was set to 0.0");
            this.price = 0.0;
            return;
        }
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryIndex() {
        return categoryIndex;
    }

    public void setCategoryIndex(int categoryIndex) {
        this.categoryIndex = categoryIndex;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("ID = ");
        builder.append(id);
        builder.append(", Company ID = ");
        builder.append(companyId);
        builder.append(", Category = ");
        builder.append(category);
        builder.append(", Title = '");
        builder.append(title);
        builder.append("', Description = '");
        builder.append(description);
        builder.append("', Start Date = ");
        builder.append(startDate);
        builder.append(", End Date = ");
        builder.append(endDate);
        builder.append(", Amount = ");
        builder.append(amount);
        builder.append(", Price = ");
        builder.append(price);
        builder.append(", Image = '");
        builder.append(image);
        builder.append('\'');

        return builder.toString();
    }

}
