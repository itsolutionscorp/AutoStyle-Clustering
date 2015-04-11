class Scrabble
  attr_reader :letters, :scorer

  def initialize(word, scorer = Scorer)
    @letters = to_letters(word)
    @scorer = scorer
  end

  def score
    scorer.get_score(letters)
  end

  def self.score(word)
    Scrabble.new(word).score
  end

  private

  def to_letters(word)
    word.to_s.downcase.gsub(/[^a-z]/, '').split('')
  end

end

class Scorer

  def self.get_score(letters)
    letters.inject(0) { |score, letter| score += scores[letter] }
  end

  private

  def self.scores
    {
      "a" => 1,
      "b" => 3,
      "c" => 3,
      "d" => 2,
      "e" => 1,
      "f" => 4,
      "g" => 2,
      "h" => 4,
      "i" => 1,
      "j" => 8,
      "k" => 5,
      "l" => 1,
      "m" => 3,
      "n" => 1,
      "o" => 1,
      "p" => 3,
      "q" => 10,
      "r" => 1,
      "s" => 1,
      "t" => 1,
      "u" => 1,
      "v" => 4,
      "w" => 4,
      "x" => 8,
      "y" => 4,
      "z" => 10
    }
  end

end
