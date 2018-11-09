package aswanabidin.football_match_finalproject.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import aswanabidin.football_match_finalproject.constants.FavoriteEventConstants
import aswanabidin.football_match_finalproject.constants.FavoriteTeamConstants
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorites.db", null, 1) {

    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteTeamConstants.TABLE, true,
            FavoriteTeamConstants.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeamConstants.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeamConstants.TEAM_NAME to TEXT,
            FavoriteTeamConstants.TEAM_BADGE to TEXT
        )

        db.createTable(FavoriteEventConstants.TABLE, true,
            FavoriteEventConstants.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteEventConstants.EVENT_ID to TEXT + UNIQUE,
            FavoriteEventConstants.HOME_NAME to TEXT,
            FavoriteEventConstants.HOME_SCORE to TEXT,
            FavoriteEventConstants.AWAY_NAME to TEXT,
            FavoriteEventConstants.AWAY_SCORE to TEXT,
            FavoriteEventConstants.DATE to TEXT,
            FavoriteEventConstants.TIME to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteTeamConstants.TABLE, true)
        db.dropTable(FavoriteEventConstants.TABLE, true)
    }

}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)