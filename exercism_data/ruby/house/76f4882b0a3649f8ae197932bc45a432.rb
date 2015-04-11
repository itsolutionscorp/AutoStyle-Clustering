class House

  def initialize
    @poem_array = []
    parse_poem
  end

  def verse(line)
    @poem_array[line-1].gsub(/\n/, " ") + "\n"
  end

  def verses(start_line, end_line)
    multiple_lines = ""
    (start_line..end_line).each do |line|
      multiple_lines << @poem_array[line-1].gsub(/\n/, " ") + "\n\n"
    end
    multiple_lines
  end

  private
  def parse_poem

    poem = "This is the house that Jack built.

This is the malt that lay in the house that Jack built.

This is the rat that ate the malt
that lay in the house that Jack built.

This is the cat that killed the rat
that ate the malt that lay in the house that Jack built.

This is the dog that worried the cat
that killed the rat that ate the malt
that lay in the house that Jack built.

This is the cow with the crumpled horn
that tossed the dog that worried the cat
that killed the rat that ate the malt
that lay in the house that Jack built.

This is the maiden all forlorn
that milked the cow with the crumpled horn
that tossed the dog that worried the cat
that killed the rat that ate the malt
that lay in the house that Jack built.

This is the man all tattered and torn
that kissed the maiden all forlorn
that milked the cow with the crumpled horn
that tossed the dog that worried the cat
that killed the rat that ate the malt
that lay in the house that Jack built.

This is the priest all shaven and shorn
that married the man all tattered and torn
that kissed the maiden all forlorn
that milked the cow with the crumpled horn
that tossed the dog that worried the cat
that killed the rat that ate the malt
that lay in the house that Jack built.

This is the rooster that crowed in the morn
that woke the priest all shaven and shorn
that married the man all tattered and torn
that kissed the maiden all forlorn
that milked the cow with the crumpled horn
that tossed the dog that worried the cat
that killed the rat that ate the malt
that lay in the house that Jack built.

This is the farmer sowing his corn
that kept the rooster that crowed in the morn
that woke the priest all shaven and shorn
that married the man all tattered and torn
that kissed the maiden all forlorn
that milked the cow with the crumpled horn
that tossed the dog that worried the cat
that killed the rat that ate the malt
that lay in the house that Jack built.

This is the horse and the hound and the horn
that belonged to the farmer sowing his corn
that kept the rooster that crowed in the morn
that woke the priest all shaven and shorn
that married the man all tattered and torn
that kissed the maiden all forlorn
that milked the cow with the crumpled horn
that tossed the dog that worried the cat
that killed the rat that ate the malt
that lay in the house that Jack built."
    @poem_array = poem.split("\n\n")

  end


end
