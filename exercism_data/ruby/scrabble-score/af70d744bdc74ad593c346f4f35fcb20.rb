class Scrabble

  LETTER_VALUES = {
    1 => %w{ A E I O U L N R S T },
    2 => %w{ D G },
    3 => %w{ B C M P },
    4 => %w{ F H V W Y },
    5 => %w{ K },
    8 => %w{ J X },
    10 => %w{ Q Z }
  }

  def initialize(word)
    word = word || ""
    @word = word.strip
  end

  def self.score(word)
    self.new(word).score
  end

  def score
    @word.split(//).inject(0){|score, char| score + letter_score(char) }
  end

  private

  def letter_score(char)
    value_hash = LETTER_VALUES.detect{|key, array| array.include?(char.upcase) }
    value_hash[0]
  end
end
