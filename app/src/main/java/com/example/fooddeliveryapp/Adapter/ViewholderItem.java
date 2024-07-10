public class ViewholderItem {
    private String itemName;
    private String itemImage;
    private String fee;

    public ViewholderItem( String itemName, String itemImage, String fee) {

        this.itemName = itemName;
        this.itemImage = itemImage;
        this.fee = fee;
    }



    public String getItemName() {
        return itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public String getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return "ViewholderItem " + ": " + itemName + " - " + itemImage + " - " + fee;
    }
}