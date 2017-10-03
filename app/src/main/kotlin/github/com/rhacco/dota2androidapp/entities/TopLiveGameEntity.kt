package github.com.rhacco.dota2androidapp.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "top_live_games")
data class TopLiveGameEntity(@PrimaryKey val server_steam_id: Long?)