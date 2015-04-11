class Scrabble
  attr_reader :word

  def initialize(word)
    @word = word.to_s.strip.downcase
  end

  def score
    word.chars.inject(0) do |total, c|
      total + LETTERS_SCORES[c]
    end
  end

  def self.score(word)
    self.new(word).score
  end

  SCORES = { %w|a e i o u l n r s t| => 1, %w|d g| => 2,
             %w|b c m p| => 3, %w|f h v w y| => 4, %w|k| => 5,
             %w|j x| => 8, %w|q z| => 10 }

  LETTERS_SCORES = Hash[
    *SCORES.map {|letters, score| letters.zip([score].cycle) }.flatten
  ]
end
