class House
  START = "This is"
  STOP  = ".\n"
  SUBJECT_AND_PREDICATE = [
    :placeholder_for_index_0,
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

  def verse(n)
    START + clause(n) + STOP
  end

  def verses(start, stop)
    (start..stop).reduce('') { |a, e| a << verse(e) + "\n" }
  end

  private

  def clause(n)
    return '' if n == 0
    " the #{subject(n)} that #{predicate(n)}" << clause(n-1)
  end

  def subject(n)
    SUBJECT_AND_PREDICATE.at(n).first
  end

  def predicate(n)
    SUBJECT_AND_PREDICATE.at(n).last
  end
end
