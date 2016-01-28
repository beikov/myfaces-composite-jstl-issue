package test;
/**
 * 
 */

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.model.SelectItem;

public class SelectItemEnumListConverter implements Converter {

    private final List<SelectItem> selectItems;

    /**
     * Default constructor for CDI.
     */
    public SelectItemEnumListConverter() {
        this.selectItems = new ArrayList<SelectItem>();
    }

    public SelectItemEnumListConverter(List<SelectItem> selectItems) {
        this.selectItems = selectItems;
    }

    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        if (value == null || value.isEmpty()) {
            return null;
        }

        for (int i = 0; i < selectItems.size(); i++) {
            Enum<?> e = (Enum<?>) selectItems.get(i).getValue();

            if ((e != null) && (e.name().equals(value))) {
                return e;
            }
        }

        throw new ConverterException("Unknown enumeration with the following name was selected: " + value);
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        if (value == null) {
            return null;
        }
        return ((Enum<?>) value).name();
    }
}
