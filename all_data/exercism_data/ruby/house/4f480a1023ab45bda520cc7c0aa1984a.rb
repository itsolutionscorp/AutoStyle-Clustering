class House
  def self.recite
    House.new.recite
  end

  def recite
    12.times.reduce([]) do |lyrics, num|
      lyrics << verse(num)
    end.join("\n")
  end

  def verse(num)
    this_is_the(num) + that(num)
  end

  NOUNS = [
    'house that Jack built.',
    'malt',
    'rat',
    'cat',
    'dog',
    'cow with the crumpled horn',
    'maiden all forlorn',
    'man all tattered and torn',
    'priest all shaven and shorn',
    'rooster that crowed in the morn',
    'farmer sowing his corn',
    'horse and the hound and the horn'
  ]

  VERBS = [
    'lay in',
    'ate',
    'killed',
    'worried',
    'tossed',
    'milked',
    'kissed',
    'married',
    'woke',
    'kept',
    'belonged to'
  ]

  def this_is_the(verse)
    "This is the #{NOUNS[verse]}\n"
  end

  def that(verse)
    return '' if verse.zero?
    (verse - 1).downto(0).reduce('') do |lyrics, i|
      lyrics + "that #{VERBS[i]} the #{NOUNS[i]}\n"
    end
  end
end
