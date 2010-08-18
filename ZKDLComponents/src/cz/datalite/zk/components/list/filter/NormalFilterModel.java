package cz.datalite.zk.components.list.filter;

import java.util.LinkedList;

/**
 * Model for normal filter - groups unit models. This is ordinary LinkedList
 * with defined generic and redefined clone method. This modification of ordinary
 * class makes code much simpler.
 *
 * 
 * @author Karel Čemus <cemus@datalite.cz>
 */
public class NormalFilterModel extends LinkedList<NormalFilterUnitModel> implements Cloneable {

    public static final String ALL = QuickFilterModel.CONST_ALL;

    public NormalFilterModel() {
        super();
        // creates empty filter model with no units
    }

    public NormalFilterModel( final NormalFilterModel normalFilterModel ) {
        super();
        addAll( normalFilterModel );
    }

    @Override
    public NormalFilterModel clone() {
        return new NormalFilterModel( this );
    }
}
