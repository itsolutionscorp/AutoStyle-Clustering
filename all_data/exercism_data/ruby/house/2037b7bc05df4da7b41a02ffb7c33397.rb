class House
  def verse(lines)
    verse_array = ["horse and the hound and the horn that belonged to the ",
                   "farmer sowing his corn that kept the ",
                   "rooster that crowed in the morn that woke the ",
                   "priest all shaven and shorn that married the ",
                   "man all tattered and torn that kissed the ",
                   "maiden all forlorn that milked the ",
                   "cow with the crumpled horn that tossed the ",
                   "dog that worried the ",
                   "cat that killed the ",
                   "rat that ate the ",
                   "malt that lay in the "]

    rhyme = "This is the "
    verse_array.each_index do |i|
      should_sing = verse_array.length-lines < i
      rhyme += verse_array[i] if should_sing
    end
    rhyme += "house that Jack built.\n"
  end

  def verses(first, last)
    i = first
    all_together = ""
    while i < last+1
      all_together += "#{self.verse(i)}\n"
      i+=1
    end
    all_together
  end
end
