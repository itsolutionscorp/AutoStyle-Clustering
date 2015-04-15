class Scrabble

  def self.score(word)
    new(word).score
  end

  def initialize(word)
    @word = String(word).strip
  end

  def score
    word.chars.reduce(0) { |score, letter| score += value(letter) }
  end

  private

  attr_reader :word

  def value(letter)
    letter_values.fetch(letter.upcase) { 0 }
  end

  def letter_values
    LETTER_VALUES
  end

  LETTER_VALUES = {
    1 => %w{ A E I O U L N R S T },
    2 => %w{ D G },
    3 => %w{ B C M P },
    4 => %w{ F H V W Y },
    5 => %w{ K },
    8 => %w{ J X },
    10 => %w{ Q Z }
  }.each_with_object({}) { |(score, letters), values|
    letters.each { |letter| values[letter] = score }
  }
  private_constant :LETTER_VALUES

end
