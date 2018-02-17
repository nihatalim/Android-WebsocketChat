package com.nihatalim.messenger.business.database.models;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Created by thecower on 2/17/18.
 */
@Entity
public class Message {
    @Id
    public long id;

    public String message;

    public ToOne<User> owner;

    public Date date;
}
