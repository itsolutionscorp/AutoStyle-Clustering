class House

  VERBS=['is', 'belonged to', 'kept', 'woke', 'married', 'kissed',
          'milked', 'tossed', 'worried', 'killed', 'ate', 'lay in']

  OBJECTS=['horse and the hound and the horn', 'farmer sowing his corn', 'rooster that crowed in the morn',
          'priest all shaven and shorn', 'man all tattered and torn', 'maiden all forlorn', 'cow with the crumpled horn',
          'dog', 'cat', 'rat', 'malt', 'house that Jack built.'] 

  def self.recite(stanza = 1, nursery_rhyme = "")
    if stanza > end_of_rhyme
      return nursery_rhyme
    else
      for line in 0...stanza
        nursery_rhyme += "#{preposition(line)} #{verb(line, stanza)} the #{object(line, stanza)}\n"
      end
      nursery_rhyme += "\n" unless stanza == end_of_rhyme
      recite(stanza += 1, nursery_rhyme)
    end
  end

  def self.preposition(line)
    line == 0 ? 'This' : 'that'
  end

  def self.verb(line, stanza)
    VERBS[line == 0 ? 0 : end_of_rhyme - stanza + line]
  end

  def self.object(line, stanza)
    OBJECTS[end_of_rhyme - stanza + line]
  end

  def self.end_of_rhyme
    OBJECTS.length
  end
end
