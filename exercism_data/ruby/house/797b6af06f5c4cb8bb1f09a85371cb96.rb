class House
  
  def verse number
    "This is %s.\n" % phrases(number)
  end
  
  def verses from, to
    (from..to).map do |no|
      verse(no) + "\n"
    end.join
  end
  
  private
  
  def phrases no
    no.downto(1).map do |no|
      phrase_of_clause no
    end.join(' ')
  end
  
  def phrase_of_clause number
    "the " + clauses.fetch(number).join(" that ")
  end
  
  def clauses
    { 
      1 => ["house", "Jack built"],
      2 => ["malt", "lay in"],
      3 => ["rat", "ate"],
      4 => ["cat", "killed"],
      5 => ["dog", "worried"],
      6 => ["cow with the crumpled horn", "tossed"],
      7 => ["maiden all forlorn", "milked"],
      8 => ["man all tattered and torn", "kissed"],
      9 => ["priest all shaven and shorn", "married"],
      10 => ["rooster", "crowed in the morn", "woke"],
      11 => ["farmer sowing his corn", "kept"],
      12 => ["horse and the hound and the horn", "belonged to"]
    }
  end

end
