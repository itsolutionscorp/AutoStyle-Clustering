class House

  def verse(number)
    return single_verse if number == 1
    proverb = starting_line
    while number > 1
      proverb << verse_for(number)
      number -= 1
    end
    proverb << closing_line
    proverb.join
  end

  def verses(start_verse, end_verse)
    result = start_verse.upto(end_verse).map { |verse_number| verse(verse_number) }.join("\n")
    result << "\n"
  end

  private

  def verse_for(number)
    "the #{noun_for(number)} that #{verb_for(number)} "
  end

  def noun_for(number)
    noun_verb[number][0]
  end

  def verb_for(number)
    noun_verb[number][1]
  end

  def single_verse
    "This is the house that Jack built.\n"
  end

  def starting_line
    ["This is "]
  end

  def closing_line
    "in the house that Jack built.\n"
  end

  def noun_verb
    {
      2 => ["malt", "lay"],
      3 => ["rat", "ate"],
      4 => ["cat", "killed"],
      5 => ["dog", "worried"],
      6 => ["cow with the crumpled horn", "tossed"],
      7 => ["maiden all forlorn", "milked"],
      8 => ["man all tattered and torn", "kissed"],
      9 => ["priest all shaven and shorn", "married"],
      10 => ["rooster", "crowed in the morn that woke"],
      11 => ["farmer sowing his corn", "kept"],
      12 => ["horse and the hound and the horn", "belonged to"]
    }
  end

end
