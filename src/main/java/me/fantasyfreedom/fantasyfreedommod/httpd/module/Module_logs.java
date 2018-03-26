package me.fantasyfreedom.fantasyfreedommod.httpd.module;

import java.io.File;
import me.fantasyfreedom.fantasyfreedommod.FantasyFreedomMod;
import me.fantasyfreedom.fantasyfreedommod.config.ConfigEntry;
import me.fantasyfreedom.fantasyfreedommod.httpd.NanoHTTPD;

public class Module_logs extends Module_file
{

    public Module_logs(FantasyFreedomMod plugin, NanoHTTPD.HTTPSession session)
    {
        super(plugin, session);
    }

    @Override
    public NanoHTTPD.Response getResponse()
    {
        if (ConfigEntry.LOGS_SECRET.getString().equals(params.get("password")))
        {
            return serveFile("latest.log", params, new File("./logs"));
        }
        else
        {
            return new NanoHTTPD.Response(NanoHTTPD.Response.Status.FORBIDDEN, NanoHTTPD.MIME_PLAINTEXT, "Incorrect password.");
        }
    }
}
