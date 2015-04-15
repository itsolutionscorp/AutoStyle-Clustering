class House
  NOUNS = ['the house that Jack built', 'the malt', 'the rat', 'the cat', 
           'the dog', 'the cow with the crumpled horn', 
           'the maiden all forlorn', 'the man all tattered and torn', 
           'the priest all shaven and shorn',
           'the rooster that crowed in the morn', 'the farmer sowing his corn',
           'the horse and the hound and the horn']
  VERBS = [nil, 'lay in', 'ate', 'killed', 'worried', 'tossed', 'milked',
           'kissed', 'married', 'woke', 'kept', 'belonged to']

  def self.recite
    (0..11).map { |n| recite_verse n }.join "\n"
  end

  def self.recite_verse n
    phrases = NOUNS[0..n].zip(VERBS).map { |x| x.compact.join "\nthat " }
    "This is #{phrases.reverse.join(' ')}.\n"
  end
end
