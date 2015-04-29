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
    @word.split(//).inject(0){|score, letter| score + letter_score(letter) }
  end

  private

  def letter_score(letter)
    value_hash = LETTER_VALUES.detect{|key, array| array.include?(letter.upcase) }
    value_hash[0]
  end
end
