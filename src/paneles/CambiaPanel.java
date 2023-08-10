package paneles;

import javax.swing.JPanel;

public class CambiaPanel {

    //
    private final JPanel container;
    private final JPanel content;

    /**
     *  @ErickMoncada controlador de los cambio de paneles
     * Constructor de clase que recibe 2 parametros para remplazar lo que aparece en el panel
     */
    public CambiaPanel(JPanel container, JPanel content) {
        this.container = container;
        this.content = content;
        this.container.removeAll();
        this.container.revalidate();
        this.container.repaint();

        this.container.add(this.content);
        this.container.revalidate();
        this.container.repaint();
    }

}//--> fin clase
