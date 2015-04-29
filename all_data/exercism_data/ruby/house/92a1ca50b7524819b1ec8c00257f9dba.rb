class House

  PHRASES =  [['house', 'Jack built'],
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
              ['horse and the hound and the horn', 'belonged to']]

  def verses(first, last)
    (first..last).map {|n| verse(n)}.join("\n") << "\n"
  end

  def verse(n)
    "This is " << PHRASES.take(n).reverse.map {|noun, verb| build_line(noun, verb)}.join(" ") << ".\n"
  end

  private
  def build_line(noun, verb)
    "the #{noun} that #{verb}"
  end
end
