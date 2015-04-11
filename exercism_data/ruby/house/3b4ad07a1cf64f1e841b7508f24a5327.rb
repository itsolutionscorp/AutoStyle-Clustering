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
    text = "This is"
    COMPONENTS[0...num].reverse.each do |subject, modifier|
      text += " the #{subject} that #{modifier}"
    end
    text += ".\n"
  end

  def verses(start, finish)
    (start..finish).inject("") do |text, verse_num|
      text << "#{verse(verse_num)}\n"
    end
  end
end
