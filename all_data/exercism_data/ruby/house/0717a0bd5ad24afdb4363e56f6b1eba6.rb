class House
  VERSES = [
    "This is ",
    "the house that Jack built.\n",
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
    verses = VERSES.slice(0..num)
    num.times.inject(verses.shift) do |v, n|
      v += verses.pop
    end
  end

  def verses(first, last)
    last.times.inject("") do |v, num|
      v += "#{verse(num + first)}\n"
    end
  end
end
