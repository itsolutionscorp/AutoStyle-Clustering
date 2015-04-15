class House
  VERSE_EMBEDS = [
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
  
  def verses verse_start, verse_end
    (verse_start..verse_end).each_with_object("") do |verse_number, ryhme|
      ryhme << verse(verse_number) << "\n"
    end
  end
  
  def verse number
    inner = VERSE_EMBEDS.take(number-1).reverse.join
    "This is #{inner}the house that Jack built.\n"
  end
end
