class House

  THIS_IS = "This is "

	PHRASES = [
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

	def verse(verse_num)
    THIS_IS + add_phrases(verse_num)
  end

	def verses(min,max)
    build_verses(verse_range(min,max))
	end

  private
  
  def add_phrases(verse_num)
    PHRASES[0..verse_num-1].reverse.join
  end

  def verse_range(min,max)
    (min..max)
  end

  def build_verses(verse_range)
    verse_range.each_with_object("") { |verse_num, rhyme|
      rhyme << verse(verse_num)
      add_newline(rhyme)
      }   
  end

  def add_newline(rhyme)
    rhyme << "\n"
  end


end
