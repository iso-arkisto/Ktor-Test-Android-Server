package com.yourname.data

import org.jetbrains.exposed.sql.Table

object LanguageTable : Table(name = "languages") {
    val id = integer(name = "id")
    val shortName = text("short_name")
    val image = varchar("image", 512)
    val creator = text("creator")
    val inceptionYear = integer("inception_year")
    val name = text("name")
    val rating = double("rating")
    val description = text("description")

    override val primaryKey = PrimaryKey(id)
}