class House
  PARTS = [['house', 'Jack built'],
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

  def self.recite
    PARTS.length.times.with_object('') do |i, m|
      verse = PARTS[0..i].reverse.each_with_object('') do |(a, b), mm|
        mm << "the #{a}#{b.match(/Jack/) ? ' ' : "\n" }that #{b} "
      end
      m << verse.strip
                .prepend('This is ')
                .concat(".\n#{"\n" unless n == PARTS.length - 1}")
    end
  end
end
