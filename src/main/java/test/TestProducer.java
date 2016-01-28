package test;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.inject.Named;

public class TestProducer {

    @Produces
    @RequestScoped
    @Named("testItems")
    public List<SelectItem> getItems() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (TestEnum e : TestEnum.values()) {
            list.add(new SelectItem(e, e.name()));
        }
        return list;
    }
    
    @Produces
    @RequestScoped
    @Named("testConverter")
    public Converter getConverter(@Named("testItems") List<SelectItem> items) {
        return new SelectItemEnumListConverter(items);
    }
}
