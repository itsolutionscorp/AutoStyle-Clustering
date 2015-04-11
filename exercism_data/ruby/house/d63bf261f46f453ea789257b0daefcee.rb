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
    "This is #{lines(num)}the house that Jack built.\n"
  end

  def verses(start, ending)
    (start..ending).map { |n| verse(n) + "\n" }.join
  end

  def lines(num)
    result = VERSES.slice(0..(num - 1)).reverse.join
  end
  
end
