package pe.senati

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="CommerceTable")
data class CommerceEntity(@PrimaryKey(autoGenerate=true) var commerceId:Long=0,
                          var name:String="",
                          var phone:String="",
                          var website:String="",
                          var isFavorite:Boolean=false)
