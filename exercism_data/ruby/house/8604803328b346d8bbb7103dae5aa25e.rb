class House
  START = "This is"
  STOP  = ".\n"

  def initialize
    @sentence = Sentence
  end

  def verse(n)
    START + clause(n) + STOP
  end

  def verses(start, stop)
    (start..stop).reduce('') { |a, e| a << verse(e) + "\n"}
  end

  private

  def clause(n)
    return '' if n == 0
    ' the ' + @sentence.subject(n) + ' that ' + @sentence.predicate(n) + clause(n-1)
  end
end


class Sentence
  SUBJECT_PREDICATE = [
    ['house', 'Jack built'],
    ['malt', 'lay in'],
    ['rat', 'ate'],
    ['cat', 'killed'],
    ['dog', 'worried'],
    ['cow with the crumpled horn', 'tossed'],
    ['maiden all forlorn', 'milked'],
    ['man all tattered and torn', 'kissed'],
    ['priest all shaven and shorn', 'married'],
    ['rooster that crowed in the morn', 'woke'],
    ['farmer sowing his corn', 'kept'],
    ['horse and the hound and the horn', 'belonged to']
  ]

  def self.subject(n)
    SUBJECT_PREDICATE[n-1].first
  end

  def self.predicate(n)
    SUBJECT_PREDICATE[n-1][1]
  end
end

# p House::ACTOR_ACTION.first

p House.new.verse(2)
