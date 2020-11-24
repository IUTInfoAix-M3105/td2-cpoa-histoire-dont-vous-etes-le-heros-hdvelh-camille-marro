/**
 * File: NodeMultipleEvents.java
 * Creation: 7 nov. 2020, Jean-Philippe.Prost@univ-amu.fr
 * Template étudiants
 */
package pracHDVELH;

import java.util.Scanner;

import myUtils.ErrorNaiveHandler;

/**
 * @author prost
 *
 */
public class Event extends NodeMultiple {
	public static final String ERROR_MSG_UNEXPECTED_END = "Sorry, for some unexpected reason the story ends here...";
	public static final String PROMPT_ANSWER = "Answer: ";
	public static final String WARNING_MSG_INTEGER_EXPECTED = "Please input a integer within range!";

	public GUIManager gui;
	public String playerAnswer = null;
	public int id;
	public int chosenPath;

	public Event(GUIManager gui, String data) {
		this.gui = gui;
		super.setData(data);
    }

    public Event() {
		this.gui = new GUIManager();
	}

	@Override
	public String toString() {
		return "Event #["+ getId() + " ([class]):" + getData();
	}

	/**
	 * @return the playerAnswer
	 */
	public String getPlayerAnswer() {
		return this.playerAnswer;
	}

	/**
	 * @param playerAnswer the playerAnswer to set
	 */
	public void setPlayerAnswer(String playerAnswer) {
		this.playerAnswer = playerAnswer;
	}

	/**
	 * @return the reader
	 */
	public Scanner getReader() {
		return this.gui.getInputReader();
	}

	/**
	 * @param reader the reader to set
	 */
	public void setReader(Scanner reader) {
		this.gui.setInputReader(reader);
	}

	/**
	 * @return the chosenPath
	 */
	public int getChosenPath() {
		return this.chosenPath;
	}

	/**
	 * @param chosenPath the chosenPath to set
	 */
	public void setChosenPath(int chosenPath) {
		this.chosenPath = chosenPath;
	}

	/* Methods */
	/**
	 * @see pracHDVELH.NodeMultiple#getData()
	 */
	public String getData() {
		return super.getData().toString();
	}

	/**
	 * @see pracHDVELH.NodeMultiple#setData(Object)
	 * @param data
	 */
	public void setData(String data) {
		super.setData(data);
	}

	/**
	 * @see pracHDVELH.NodeMultiple#getDaughter(int)
	 */
	@Override
	public Event getDaughter(int i) {
		return (Event) super.getDaughter(i);
	}

	/**
	 * @see pracHDVELH.NodeMultiple#setDaughter(NodeMultiple, int)
	 * @param daughter
	 * @param i
	 */
	public void setDaughter(Event daughter, int i) {
		super.setDaughter(daughter, i);
	}

	/**
	 * @return the gui
	 */
	public GUIManager getGui() {
		return this.gui;
	}

	/**
	 * @param gui the gui to set
	 */
	public void setGui(GUIManager gui) {
		this.gui = gui;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/* Methods */
	public boolean isFinal() {
		int i = 0;
		while (i != daughters.length) {
			if (daughters[i] != null) return false;
			i = i + 1;
		}
		return true;
	}

	public boolean isInRange(int index) {
		if (index >= 0 && index < this.daughters.length && this.daughters[index] != null) {
			return true;
		}
		return false;
	}

	public int interpretAnswer() {
		int playerAnswerInt;
		this.gui.outputln(this.PROMPT_ANSWER);

		playerAnswerInt = Integer.parseInt(this.getPlayerAnswer());

		while (!this.isInRange(playerAnswerInt-1)) {
			this.gui.output(WARNING_MSG_INTEGER_EXPECTED);
			this.gui.outputln(this.PROMPT_ANSWER);

			playerAnswerInt = Integer.parseInt(this.getPlayerAnswer());
		}

		this.chosenPath = chosenPath - 1;
		return this.chosenPath;
	}

	public Event run() {
		this.gui.outputln(this.getData());
		int userChoice = this.interpretAnswer();
		return this.getDaughter(userChoice);
	}
}

// eof