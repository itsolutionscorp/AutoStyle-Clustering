# class to calculate scrabble scores
class Scrabble
  SCORES = {
    'a' => 1, 'b' => 3,  'c' => 3, 'd' => 2, 'e' => 1,
    'f' => 4, 'g' => 2,  'h' => 4, 'i' => 1, 'j' => 8,
    'k' => 5, 'l' => 1,  'm' => 3, 'n' => 1, 'o' => 1,
    'p' => 3, 'q' => 10, 'r' => 1, 's' => 1, 't' => 1,
    'u' => 1, 'v' => 4,  'w' => 4, 'x' => 8, 'y' => 4,
    'z' => 10, '' => 0
  }

  def initialize(word)
    @word = clean_word word
  end

  def score
    @word.chars.reduce(0) { |a, e| a + letter_score(e) }
  end

  def self.score(word)
    Scrabble.new(word).score
  end

  private

  def clean_word(word)
    word ? word.gsub(/\W/, '') : ''
  end

  def letter_score(letter)
    SCORES.fetch(letter.downcase)
  end
end
