import org.csvHandler.Entity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.csvHandler.Util.rezultCounter;

public class testing {

    @Test
    public void createEntity(){
        Entity entity = new Entity("mike","5,6,4");
        Assert.assertEquals("mike", entity.getName());
        Assert.assertEquals("5,6,4", entity.getValue());
        Assert.assertEquals(15, entity.getSum());
        Assert.assertFalse(entity.getArrayList().isEmpty());
    }
    @Test
    public void createEntityFalse(){
        Entity entity2 = new Entity("mike","sdf");
        Assert.assertEquals(0, entity2.getSum());
    }

    @Test
    public void setNameEntity(){
        Entity entity3 = new Entity("mike","5,6,4");
        entity3.setName("newName");
        Assert.assertEquals("newName", entity3.getName());
    }

    @Test
    public void setValueEntity(){
        Entity entity = new Entity("mike","1");
        entity.setValue("srtr");
        Assert.assertEquals(0, entity.setSumArray());
    }

    @Test
    public void checkRezultCounter(){
        ArrayList<String[]> str = new ArrayList<>();

        str.add(new String[]{"mike", "45"});
        str.add(new String[]{"Mike", "45"});
        str.add(new String[]{"MIKE", "45"});

        ArrayList<Entity> list = new ArrayList<>();
        list.add(new Entity("mike", "45,45,45"));

        Assert.assertEquals(list.toString(), rezultCounter(str).toString());

    }



}
