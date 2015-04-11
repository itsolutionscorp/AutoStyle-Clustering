class House

  COMPONENTS = [['house', 'Jack built'],
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

  def verse(num)
    "This is" + COMPONENTS[0...num].reverse.map do |subject, modifier|
      " the #{subject} that #{modifier}"
    end.join + ".\n"
  end

  def verses(start, finish)
    (start..finish).map { |verse_num| "#{verse(verse_num)}\n" }.join
  end
end
