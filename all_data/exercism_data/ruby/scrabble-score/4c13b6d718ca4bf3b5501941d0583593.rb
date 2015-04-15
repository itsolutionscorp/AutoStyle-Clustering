class Scrabble
  # This exercise would be more interesting with the
  # double/triple letter/word scoring.  Though I can
  # see how representing that would be quite difficult
  # to represent in the API as it is.  :P

  def self.score(word)
    new(word).score
  end

  attr_reader :word
  def initialize(word)
    @word = word
  end

  def score
    letter_values.inject(&:+) || 0
  end

  def letter_values
    normalized.chars.map {|char| LETTER_VALUE[char]}
  end

  def normalized
    word.to_s.strip.downcase
  end

  LETTER_VALUE = {
    "a"=>1, "b"=>3, "c"=>3, "d"=>2, "e"=>1,
    "f"=>4, "g"=>2, "h"=>4, "i"=>1, "j"=>8,
    "k"=>5, "l"=>1, "m"=>3, "n"=>1, "o"=>1,
    "p"=>3, "q"=>10, "r"=>1, "s"=>1, "t"=>1,
    "u"=>1, "v"=>4, "w"=>4, "x"=>8, "y"=>4,
    "z"=>10
  }

end
