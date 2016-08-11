package org.mt.business.control.repository.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;
import javax.inject.Inject;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mt.business.api.entity.util.SortCriteria;
import org.mt.business.api.entity.util.SortCriteria.SortDirection;

import com.mongodb.WriteResult;

/**
 * Abstract DAO class for base CRUD operations on entities. <br>
 *
 * @param <EI>
 *            Entity interface
 * @param <EC>
 *            Entity concrete class (annotated with Morphia annotations) and
 *            implements EI.
 */
public abstract class AbstractRepository<EI, EC extends EI> {

    @Inject
    protected Datastore ds;

    /**
     * Transformer function for SortCriteria to string appropriate for Morphia
     * order condition
     */
    protected static final Function<SortCriteria, String> ORDER_TRANSFORMER = sc -> {

	final List<String> fields = sc.getCriteria().entrySet().stream()
		.map(e -> SortDirection.desc.equals(e.getValue()) ? "-" + e.getKey() : e.getKey())
		.collect(Collectors.toList());

	return fields != null && !fields.isEmpty() ? String.join(",", fields) : "";
    };

    public abstract Class<EC> getEntityClazz();

    @CacheRemoveAll
    public void save(EI entity) {

	Objects.requireNonNull(entity);

	ds.save(entity);
    }

    @CacheRemoveAll
    public void save(Collection<EI> entities) {

	Objects.requireNonNull(entities);

	if (entities.isEmpty()) {
	    throw new IllegalArgumentException("Empty entities collection passed for save.");
	}

	ds.save(entities);
    }

    @CacheResult
    public EI findById(Object id) {

	Objects.requireNonNull(id);

	return ds.get(getEntityClazz(), id);
    }

    @SuppressWarnings("unchecked")
    @CacheResult
    public List<EI> findByIds(Collection<?> ids) {

	Objects.requireNonNull(ids);

	if (ids.isEmpty()) {
	    throw new IllegalArgumentException("Empty ids collection passed.");
	}

	final Query<EC> query = ds.get(getEntityClazz(), ids);

	final List<EI> results = (List<EI>) query.asList();

	if (results == null) {
	    return Collections.emptyList();
	}

	return results;
    }

    public long getCount() {

	return ds.getCount(getEntityClazz());
    }

    @CacheRemoveAll
    public boolean deleteById(Object id) {

	Objects.requireNonNull(id);

	final WriteResult wr = ds.delete(getEntityClazz(), id);

	if (wr.getN() > 0) {

	    return true;
	}

	return false;
    }

    @CacheRemoveAll
    public int deleteByIds(Collection<?> ids) {

	Objects.requireNonNull(ids);

	if (ids.isEmpty()) {
	    throw new IllegalArgumentException("Empty ids collection passed.");
	}

	final WriteResult wr = ds.delete(getEntityClazz(), ids);

	return wr.getN();
    }

}
