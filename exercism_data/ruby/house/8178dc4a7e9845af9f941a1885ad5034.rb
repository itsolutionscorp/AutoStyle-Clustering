class House
  VERSES = [
    "",
    "the malt that lay in ",
    "the rat that ate ",
    "the cat that killed ",
    "the dog that worried ",
    "the cow with the crumpled horn that tossed ",
    "the maiden all forlorn that milked ",
    "the man all tattered and torn that kissed ",
    "the priest all shaven and shorn that married ",
    "the rooster that crowed in the morn that woke ",
    "the farmer sowing his corn that kept ",
    "the horse and the hound and the horn that belonged to "
  ]

  def verse(num)
      "This is #{clauses(num)}the house that Jack built.\n"
  end

  def verses(start, ending)
      (start..ending).map { |n| verse(n) + "\n" }.join
  end

  def clauses(num)
    sum_of_clauses = String.new
    i = num - 1
    while i >= 0
      sum_of_clauses += VERSES[i]
      i = i - 1
    end
    return sum_of_clauses
  end


end
