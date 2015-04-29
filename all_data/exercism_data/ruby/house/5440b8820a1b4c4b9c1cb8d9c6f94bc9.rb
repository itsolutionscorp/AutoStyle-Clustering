class House
  
  def verse number
    embed = number.downto(1).map do |clause_no|
      clauses[clause_no] 
    end.join(' ')
    phrase % embed
  end
  
  def verses from, to
    (from..to).map do |no|
      verse(no) + "\n"
    end.join('')
  end
  
  private
  
  def phrase
    "This is %s.\n"
  end
  
  def clauses
    { 
      1 => "the house that Jack built",
      2 => "the malt that lay in",
      3 => "the rat that ate",
      4 => "the cat that killed",
      5 => "the dog that worried",
      6 => "the cow with the crumpled horn that tossed",
      7 => "the maiden all forlorn that milked",
      8 => "the man all tattered and torn that kissed",
      9 => "the priest all shaven and shorn that married",
      10 => "the rooster that crowed in the morn that woke",
      11 => "the farmer sowing his corn that kept",
      12 => "the horse and the hound and the horn that belonged to"
    }
  end

end

puts House.new.verses(1,3)
