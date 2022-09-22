package supaHotFire;

import robocode.Robot;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;

public class SupaHotFire extends Robot {
    public void turnGunRightRelative() {
        // if (getGunHeat() < 1) {
            double optimalRadarAngle = Math.atan2(getBattleFieldHeight(), getBattleFieldWidth()) * 90;
            System.out.println("optimalRadarAngle: " + optimalRadarAngle);
            turnGunRight(normalRelativeAngleDegrees(getHeading() - getGunHeading() + optimalRadarAngle));
        // };
    }

    @Override
    public void run() {
        double distanceToTopWall = getBattleFieldHeight() - getY();

        turnRight(normalRelativeAngleDegrees(-getHeading()));
        ahead(distanceToTopWall);
        turnRight(90);
        turnGunRight(90);


        while (true) {
            double distanceToRightWall = getBattleFieldWidth() - getX();
            ahead(distanceToRightWall);
            turnRight(90);
            turnGunRightRelative();

            double distanceToBottomWall = getY();
            ahead(distanceToBottomWall);
            turnRight(90);
            turnGunRightRelative();

            double distanceToLeftWall = getX();
            ahead(distanceToLeftWall);
            turnRight(90);
            turnGunRightRelative();

            distanceToTopWall = getBattleFieldHeight() - getY();
            ahead(distanceToTopWall);
            turnRight(90);
            turnGunRightRelative();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        if (getVelocity() == 0) {
            return;
        }

        turnGunRight(normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading()));

        if (e.getDistance() > 100) {
            fire(1);
        } else {
            fire(3);
        }
    }
}
