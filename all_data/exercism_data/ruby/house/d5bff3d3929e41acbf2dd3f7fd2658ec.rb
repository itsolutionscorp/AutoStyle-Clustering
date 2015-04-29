class House
  CLAUSES = [
    ['house', "Jack built.\n"],
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
    ['horse and the hound and the horn', 'belonged to'],
  ]

  def verse(clauses_nb)
    clauses_nb.downto(1).each_with_object('This is') do |i, phrase|
      thing, did = CLAUSES[i-1]
      phrase << ' the ' + thing + ' that ' + did
    end
  end

  def verses(first, last)
    (first..last).map { |nb| verse(nb) + "\n" }.join
  end
end
