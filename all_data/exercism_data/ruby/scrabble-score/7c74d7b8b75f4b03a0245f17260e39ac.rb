class Scrabble
  LETTER_VALUES = {
    1 => ['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'],
    2 => ['D','G'],
    3 => ['B','C','M','P'],
    4 => ['F', 'H', 'V', 'W', 'Y'],
    5 => ['K'],
    8 => ['J', 'X'],
    10 => ['Q', 'Z'],
  }

  class << self
    def score(word)
      self.new(word).score
    end
  end

  attr_reader :word

  def initialize(word)
    @word = word
  end

  def score
    return calculate_score if word
    return 0
  end

  private

  def calculate_score
    word.chars.reduce(0) do |sum, letter|
      LETTER_VALUES.each do |point, letters|
        sum += point if letters.include?(letter.upcase)
      end
      sum
    end
  end
end
