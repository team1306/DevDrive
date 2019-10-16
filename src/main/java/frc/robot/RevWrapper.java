package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class RevWrapper extends REVDigitBoard {

    private final int maxPot = 5;

    /**
     * Displays a message, waits for the user to start input, and waits for a button
     * press to confirm the inupt.
     * 
     * @return the value of the potentiometer at the time of press
     */
    public double AskAnalog() {
        return AskAnalog("    ");
    }

    /**
     * Displays a message, waits for the user to start input, and waits for a button
     * press to confirm the inupt.
     * 
     * @return the value of the potentiometer at the time of press
     */
    public double AskAnalog(String message) {
        // Display message and record potentiometer value to determine when dial is
        // turned
        this.display(message);
        awaitPotChange(0.01);
        // dial has been moved, update user as to its position until
        double returnValue = this.getPot();
        while (getButtonA() && getButtonB()) {// Tweak this value for changes to sensitvity
            Timer.delay(0.05);
            returnValue = getPot();
            display(returnValue);
        }
        return returnValue;
    }

    /**
     * Displays a message, waits for the user to start input, and waits for a button
     * press to confirm the inupt.
     * 
     * @return the value of the potentiometer at the time of press
     */
    public double AskAnalog(String message, String[] options) {
        // Display message and record potentiometer value to determine when dial is
        // turned
        this.display(message);
        awaitPotChange(0.01);
        // dial has been moved, update user as to its position until
        int optionIndex = 0;
        while (getButtonA() && getButtonB()) {// Tweak this value for changes to sensitvity
            Timer.delay(0.05);
            optionIndex = (int) (0.99 * options.length * getPot() / maxPot);
            display(optionIndex);
        }
        return optionIndex;
    }

    /** 
    *Blocks until the potentiometer is moved past a certain tolerance
    */
    private void awaitPotChange(double tolerance) {
        double origPot = this.getPot();
        while (Math.abs(origPot - this.getPot()) < tolerance) {// Tweak this value for changes to sensitvity
            Timer.delay(0.05);
        }
    }

    /**
     * Blocks until a button is pressed
     */
    public void awaitButtonPress(){
        //for some reason the buttons default to true
        System.out.println(getButtonA());
            System.out.println(getButtonB());
        while (getButtonA() && getButtonB()) {// Tweak this value for changes to sensitvity
            Timer.delay(0.05);
            
        }
    }
}