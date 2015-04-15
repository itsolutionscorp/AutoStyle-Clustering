class House
  LAST = "the house that Jack built.\n"
  RHYME_CONTENTS = 
    [" malt that lay in ",
    " rat that ate ",
    " cat that killed ",
    " dog that worried ",
    " cow with the crumpled horn that tossed ",
    " maiden all forlorn that milked ",
    " man all tattered and torn that kissed ",
    " priest all shaven and shorn that married ",
    " rooster that crowed in the morn that woke ",
    " farmer sowing his corn that kept ",
    " horse and the hound and the horn that belonged to "]

  def verse(number)
    create_rhyme(number)
  end

  def verses(first,last)
    (first..last).map { |number| create_rhyme(number) + "\n" }.join('')
  end

  def create_rhyme(depth)
    (0..depth-2).reverse_each.reduce('This is ') { |rhyme, position| rhyme << 'the' + RHYME_CONTENTS[position] } + LAST
  end
  
end
