class House
  
  def verses(first, last)
    first.upto(last).collect do |i|
      verse(i)
    end.join("\n") + "\n"
  end

  def verse(number)
    "This is" "%s.\n" % sequence(number).join('')
  end

  def sequence(length)
    fragments.take(length).reverse.map do |contingent, association|
      " the #{contingent} that #{association}" 
    end
  end

  private

  def fragments
    [
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
      ['horse and the hound and the horn', 'belonged to'],
    ]
  end
end
