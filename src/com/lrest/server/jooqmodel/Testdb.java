/**
 * This class is generated by jOOQ
 */
package com.lrest.server.jooqmodel;


import com.lrest.server.jooqmodel.tables.Tusers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Testdb extends SchemaImpl {

    private static final long serialVersionUID = 46742699;

    /**
     * The reference instance of <code>testdb</code>
     */
    public static final Testdb TESTDB = new Testdb();

    /**
     * The table <code>testdb.tusers</code>.
     */
    public final Tusers TUSERS = com.lrest.server.jooqmodel.tables.Tusers.TUSERS;

    /**
     * No further instances allowed
     */
    private Testdb() {
        super("testdb", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Tusers.TUSERS);
    }
}
