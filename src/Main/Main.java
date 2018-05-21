/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.Controller;
import GUI.MainView;
import View.View;
import deepspace.GameUniverse;

/**
 *
 * @author victor
 */
public class Main {
        public static void main(String[] args) {
             GameUniverse game = new GameUniverse();
             View view = new MainView();
             Controller c = new Controller(game,view);
             c.start();
        }
}
