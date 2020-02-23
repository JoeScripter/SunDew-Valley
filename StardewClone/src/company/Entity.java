package company;

public class Entity {

    private EntityInformation entityInformation;

    public Entity(int x, int y, int width, int height, boolean passable) {
        this.entityInformation = new EntityInformation(x, y, width, height, passable);
    }

    public EntityInformation getEntityInformation() {
        return entityInformation;
    }

    public void setEntityInformation(EntityInformation entityInformation) {
        this.entityInformation = entityInformation;
    }
}
