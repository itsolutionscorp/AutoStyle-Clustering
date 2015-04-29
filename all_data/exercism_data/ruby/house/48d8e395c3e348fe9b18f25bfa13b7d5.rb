class House
  def verse(number)
    "This is" + message(number - 1) + "\n"
  end

  def verses(start, stop)
    output = ""

    (start..stop).each do |i|
      output += verse(i) + "\n"
    end

    output
  end

  private

  def message(number)
    rhyme = ["", " the " + creatures[number], " that ", actions[number]]
    rhyme << message(number - 1) if number > 1
    rhyme.join
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
