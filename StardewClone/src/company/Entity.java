package company;

public class Entity {

    private EntityInformation entityInformation;

    public Entity(int x, int y, int width, int height) {
        this.entityInformation = new EntityInformation(x, y, width, height);
    }

    public EntityInformation getEntityInformation() {
        return entityInformation;
    }

    public void setEntityInformation(EntityInformation entityInformation) {
        this.entityInformation = entityInformation;
    }
}
