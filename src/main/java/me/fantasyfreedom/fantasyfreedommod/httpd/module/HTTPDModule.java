package me.fantasyfreedom.fantasyfreedommod.httpd.module;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import me.fantasyfreedom.fantasyfreedommod.FantasyFreedomMod;
import me.fantasyfreedom.fantasyfreedommod.httpd.HTTPDPageBuilder;
import me.fantasyfreedom.fantasyfreedommod.httpd.NanoHTTPD.HTTPSession;
import me.fantasyfreedom.fantasyfreedommod.httpd.NanoHTTPD.Method;
import me.fantasyfreedom.fantasyfreedommod.httpd.NanoHTTPD.Response;
import me.fantasyfreedom.fantasyfreedommod.util.FLog;
import net.pravian.aero.component.PluginComponent;

public abstract class HTTPDModule extends PluginComponent<FantasyFreedomMod>
{

    protected final String uri;
    protected final Method method;
    protected final Map<String, String> headers;
    protected final Map<String, String> params;
    protected final Socket socket;
    protected final HTTPSession session;

    public HTTPDModule(FantasyFreedomMod plugin, HTTPSession session)
    {
        super(plugin);
        this.uri = session.getUri();
        this.method = session.getMethod();
        this.headers = session.getHeaders();
        this.params = session.getParms();
        this.socket = session.getSocket();
        this.session = session;
    }

    public String getBody()
    {
        return null;
    }

    public String getTitle()
    {
        return null;
    }

    public String getStyle()
    {
        return null;
    }

    public String getScript()
    {
        return null;
    }

    public Response getResponse()
    {
        return new HTTPDPageBuilder(getBody(), getTitle(), getStyle(), getScript()).getResponse();
    }

    protected final Map<String, String> getFiles()
    {
        Map<String, String> files = new HashMap<>();

        try
        {
            session.parseBody(files);
        }
        catch (Exception ex)
        {
            FLog.severe(ex);
        }

        return files;
    }
}
