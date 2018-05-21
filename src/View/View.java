/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Controller.*;
import deepspace.CombatResult;
import java.util.ArrayList;

/**
 *
 * @author victor
 */
public interface View {
    public void setController (Controller c);
    public void updateView();
    public void showView();
    public ArrayList<String> getNames();
    public boolean confirmExitMessage();

    public void resultadoCombate(CombatResult c);

    public void pendingDamageMessage();
}
