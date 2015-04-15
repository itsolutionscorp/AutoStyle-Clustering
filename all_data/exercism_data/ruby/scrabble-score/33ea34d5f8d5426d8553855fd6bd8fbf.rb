class Scrabble
  def self.score(word)
    new(word).score
  end

  LETTER_SCORES = {
    'd' => 2, 'g' => 2,
    'b' => 3, 'c' => 3, 'm' => 3, 'p' => 3,
    'f' => 4, 'h' => 4, 'v' => 4, 'w' => 4, 'y' => 4,
    'k' => 5,
    'j' => 8, 'x' => 8,
    'q' => 10, 'z' => 10
  }

  attr_reader :word

  def initialize(word)
    @word = "#{word}".strip.downcase
  end

  def score
    word.chars.inject(0) {|sum, letter| sum + letter_score(letter) }
  end

  private

  def letter_score(letter)
    LETTER_SCORES.fetch(letter, 1)
  end
end
