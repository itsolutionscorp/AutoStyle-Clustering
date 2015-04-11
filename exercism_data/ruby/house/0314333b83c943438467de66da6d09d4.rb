class House
  VERSES = [
    "the horse and the hound and the horn that belonged to ",
    "the farmer sowing his corn that kept ",
    "the rooster that crowed in the morn that woke ",
    "the priest all shaven and shorn that married ",
    "the man all tattered and torn that kissed ",
    "the maiden all forlorn that milked ",
    "the cow with the crumpled horn that tossed ",
    "the dog that worried ",
    "the cat that killed ",
    "the rat that ate ",
    "the malt that lay in ",
    "the house that Jack built.\n"
  ]

  def verse(verse_number)
    verse = "This is "
    verse << VERSES[-verse_number, verse_number].join
  end

  def verses(start, finish)
    verses = ""
    (start..finish).each { |v| verses << verse(v) << "\n" }
    verses
  end

end
