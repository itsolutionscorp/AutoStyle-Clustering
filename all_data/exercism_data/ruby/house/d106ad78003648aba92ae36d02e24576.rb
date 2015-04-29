class House
  def verse(number)
    body = PHRASES.take(number).reverse.join(' ')
    "This is #{body}.\n"
  end

  def verses(start, last)
    (start..last).map { |number| "#{verse(number)}\n" }.join
  end

  private

  PHRASES = [
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
  ].map { |subject, action| "the #{subject} that #{action}" }
end
