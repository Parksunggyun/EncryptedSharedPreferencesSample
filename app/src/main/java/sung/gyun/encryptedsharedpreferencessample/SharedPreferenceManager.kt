package sung.gyun.encryptedsharedpreferencessample

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SharedPreferenceManager {

    companion object {

        fun getBasicSharedPref(context: Context): SharedPreferences {
            return context.getSharedPreferences(BASIC_PREF_NAME, Context.MODE_PRIVATE)
        }

        fun getEncryptedSharedPref(context: Context): SharedPreferences {

            val masterKeyAlias by lazy {
                MasterKey
                    .Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build()
            }
            return EncryptedSharedPreferences.create(
                context,
                ENCRYPTED_PREF_NAME,
                masterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // AES256_SIV으로 key를 암호화
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // AES256_GCM으로 value를 암호화
            )
        }

        fun migrate(context: Context) {
            with(getBasicSharedPref(context)) {
                if(this.all.isNotEmpty()) {
                    copyTo(context, this)
                }
            }
        }

        private fun copyTo(context: Context, sharedPreferences: SharedPreferences) {
            val editor = getEncryptedSharedPref(context).edit()
            val keys = sharedPreferences.all
            for(entry in keys.entries) {
                val key = entry.key
                val value = entry.value
                Log.d("map values", "$key: $value")
                if(value is Int) {
                    editor.putInt(key, value).apply()
                    sharedPreferences.edit().remove(key).apply()
                }
                if(value is Boolean) {
                    editor.putBoolean(key, value).apply()
                    sharedPreferences.edit().remove(key).apply()
                }
                if(value is Long) {
                    editor.putLong(key, value).apply()
                    sharedPreferences.edit().remove(key).apply()
                }
                if(value is Float) {
                    editor.putFloat(key, value).apply()
                    sharedPreferences.edit().remove(key).apply()
                }
                if(value is String) {
                    editor.putString(key, value).apply()
                    sharedPreferences.edit().remove(key).apply()
                }
            }
        }


        private const val BASIC_PREF_NAME = "basic_pref_name"
        private const val ENCRYPTED_PREF_NAME = "encrypted_pref_name"
    }

}