class House

  def initialize
    @verse_initializer = "This is the"

    @verse_parts = [
      " house that Jack built.\n",
      " malt that lay in the",
      " rat that ate the",
      " cat that killed the",
      " dog that worried the",
      " cow with the crumpled horn that tossed the",
      " maiden all forlorn that milked the",
      " man all tattered and torn that kissed the",
      " priest all shaven and shorn that married the",
      " rooster that crowed in the morn that woke the",
      " farmer sowing his corn that kept the",
      " horse and the hound and the horn that belonged to the"
    ]
  end

  def verse verse_num
    gen_verse @verse_initializer, verse_num - 1
  end

  def verses verse_num_start, verse_num_end
    verses = ""
    (verse_num_start..verse_num_end).each do |verse_num|
      verses += verse(verse_num) + "\n"
    end
    verses
  end

  private

  def gen_verse verse_in_progress, verse_part_indx
    if verse_part_indx < 0
      verse_in_progress
    else
      gen_verse verse_in_progress + @verse_parts[verse_part_indx], verse_part_indx - 1
    end
  end

end
