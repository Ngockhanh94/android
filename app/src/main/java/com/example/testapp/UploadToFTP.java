package com.example.testapp;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.testapp.Model.Report.RealPathUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.File;

public class UploadToFTP extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    public void connectFTP() throws JSchException {
        String user = "huongpn";
        String password = "76210119";
        String host = "104.154.118.39";
        int port=21;

        JSch ssh = new JSch();
        Session session = ssh.getSession(user, host, port);
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setPassword(password);
        Channel channel = session.openChannel("sftp");
        channel.connect();
    }
}
