class Scrabble < Struct.new(:word)
  def self.score(word)
    Scrabble.new(word).score
  end

  def score
    letters.map { |letter| letter_values[letter] }.compact.inject(:+) || 0
  end

  private

  def letters
    word.to_s.upcase.chars
  end

  def letter_values
    Hash[*LETTER_VALUES.map do |letters, value|
      letters.map { |letter| [letter, value] }
    end.flatten]
  end

  LETTER_VALUES = {
    %w(A E I O U L N R S T) => 1,
    %w(D G) => 2,
    %w(B C M P) => 3,
    %w(F H V W Y) => 4,
    %w(K) => 5,
    %w(J X) => 8,
    %w(Q Z) => 10
  }
end
