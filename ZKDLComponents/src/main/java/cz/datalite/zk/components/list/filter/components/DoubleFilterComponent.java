package cz.datalite.zk.components.list.filter.components;

import org.zkoss.zul.Doublebox;

/**
 * Standard implementation of the filter component for doubleboxes. There are
 * no validation additions, each of restrictions comes from the component.
 * @author Karel Cemus
 */
public class DoubleFilterComponent extends AbstractFilterComponent<Doublebox> {

    public DoubleFilterComponent() {
        super( new Doublebox() );
    }

    public FilterComponent cloneComponent() {
        return new DoubleFilterComponent();
    }
}
