package com.nihatalim.messenger.business.database.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by thecower on 2/17/18.
 */
@Entity
public class User {
    @Id
    public long id;

    public String sessionID;

    public String name;

}
