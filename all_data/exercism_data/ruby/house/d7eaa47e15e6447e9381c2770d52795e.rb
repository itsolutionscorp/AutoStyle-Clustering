class House
  def verse(number)
    "This is" + message(number - 1) + "\n"
  end

  def verses(start, stop)
    i = start
    output = ""

    while i <= stop
      output += verse(i) + "\n"
      i += 1
    end

    output
  end

  private

  def message(number)
    rhyme = ""
    rhyme += " the " + creatures[number]
    rhyme += " that "
    rhyme += actions[number]
    number > 1 ? rhyme += message(number - 1) : rhyme
  end

  def creatures
    [ "house",
      "malt",
      "rat",
      "cat",
      "dog",
      "cow with the crumpled horn",
      "maiden all forlorn",
      "man all tattered and torn",
      "priest all shaven and shorn",
      "rooster that crowed in the morn",
      "farmer sowing his corn",
      "horse and the hound and the horn" ]
  end

  def actions
    [ "Jack built.",
      "lay in the house that Jack built.",
      "ate",
      "killed",
      "worried",
      "tossed",
      "milked",
      "kissed",
      "married",
      "woke",
      "kept",
      "belonged to"]
  end
end
