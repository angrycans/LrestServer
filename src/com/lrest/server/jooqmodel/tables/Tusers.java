/**
 * This class is generated by jOOQ
 */
package com.lrest.server.jooqmodel.tables;


import com.lrest.server.jooqmodel.Keys;
import com.lrest.server.jooqmodel.Testdb;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class Tusers extends TableImpl<Record> {

    private static final long serialVersionUID = 1854122526;

    /**
     * The reference instance of <code>testdb.tusers</code>
     */
    public static final Tusers TUSERS = new Tusers();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>testdb.tusers.id</code>.
     */
    public final TableField<Record, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>testdb.tusers.name</code>.
     */
    public final TableField<Record, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(36), this, "");

    /**
     * The column <code>testdb.tusers.password</code>.
     */
    public final TableField<Record, String> PASSWORD = createField("password", org.jooq.impl.SQLDataType.VARCHAR.length(16), this, "");

    /**
     * The column <code>testdb.tusers.token</code>.
     */
    public final TableField<Record, String> TOKEN = createField("token", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

    /**
     * Create a <code>testdb.tusers</code> table reference
     */
    public Tusers() {
        this("tusers", null);
    }

    /**
     * Create an aliased <code>testdb.tusers</code> table reference
     */
    public Tusers(String alias) {
        this(alias, TUSERS);
    }

    private Tusers(String alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Tusers(String alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Testdb.TESTDB;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<Record, Integer> getIdentity() {
        return Keys.IDENTITY_TUSERS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<Record> getPrimaryKey() {
        return Keys.KEY_TUSERS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<Record>> getKeys() {
        return Arrays.<UniqueKey<Record>>asList(Keys.KEY_TUSERS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tusers as(String alias) {
        return new Tusers(alias, this);
    }

    /**
     * Rename this table
     */
    public Tusers rename(String name) {
        return new Tusers(name, null);
    }
}
