package pl.pijok.tpo6_bj_s24322;

import java.sql.SQLException;

public class BaseRunner extends Thread {

    @Override
    public void run() {
        super.run();

        while(!isInterrupted()) {
            try {
                Thread.sleep(30 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String sql = "SELECT 1;";

            try {
                DataSource.getConnection().prepareStatement(sql).executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
