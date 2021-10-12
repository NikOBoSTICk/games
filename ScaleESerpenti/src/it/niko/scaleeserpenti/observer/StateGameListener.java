package it.niko.scaleeserpenti.observer;

import it.niko.scaleeserpenti.config.Configuration;
import it.niko.scaleeserpenti.game.PlayerState;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.HashMap;

public class StateGameListener extends JPanel implements GameListener {

    private final HashMap<String, JLabel[]> data;
    private final JPanel statePanel;

    public StateGameListener() {
        data = new HashMap<>();
        statePanel = new JPanel();
        JPanel title = new JPanel();
        title.setLayout(new GridLayout(1, 4));
        title.add(new JLabel("Ban"), SwingConstants.CENTER);
        title.add(new JLabel("Stops"), SwingConstants.CENTER);
        title.add(new JLabel("Position"), SwingConstants.CENTER);
        title.add(new JLabel("Player"), SwingConstants.CENTER);
        setLayout(new BorderLayout());
        add(title, BorderLayout.NORTH);
        add(statePanel, BorderLayout.CENTER);
    }

    @Override
    public void update(GameEvent e) {
        Game game = e.getSrc();
        switch(e.getEventType()) {
            case CONFIG -> {
                Configuration config = game.getConfiguration();
                statePanel.removeAll();
                statePanel.setLayout(new GridLayout(config.getNumPlayers() , 4, 2, 2));
                statePanel.setBackground(Color.BLACK);
                statePanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
                for(int i=1; i<=config.getNumPlayers(); i++) {
                    JLabel[] state = new JLabel[4];
                    JLabel player = new JLabel("Player" + i, SwingConstants.CENTER);
                    JLabel pos = new JLabel("0", SwingConstants.CENTER);
                    JLabel stops = new JLabel("0", SwingConstants.CENTER);
                    JLabel ban = new JLabel("n", SwingConstants.CENTER);
                    player.setOpaque(true);
                    player.setBackground(Color.CYAN);
                    pos.setOpaque(true);
                    pos.setBackground(Color.YELLOW);
                    stops.setOpaque(true);
                    stops.setBackground(Color.RED);
                    ban.setOpaque(true);
                    ban.setBackground(Color.GREEN);
                    state[0] = player;
                    state[1] = pos;
                    state[2] = stops;
                    state[3] = ban;
                    statePanel.add(state[0]);
                    statePanel.add(state[1]);
                    statePanel.add(state[2]);
                    statePanel.add(state[3]);
                    data.put("Player " + i, state);
                }
            }
            case ROUND -> {
                PlayerState state = game.getCurrentPlayerState();
                JLabel[] v = data.get(state.name());
                v[1].setText(String.valueOf(state.pos()));
                v[2].setText(String.valueOf(state.stops()));
                v[3].setText(state.ban()?"y":"n");
                data.put(state.name(), v);
            }
        }
        repaint();
        revalidate();
    }
}