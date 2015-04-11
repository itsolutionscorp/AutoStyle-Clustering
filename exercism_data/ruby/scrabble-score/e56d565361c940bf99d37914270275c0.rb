class Scrabble

  attr_reader :word

  def initialize(word)
    @word = word.to_s.downcase
  end

  def self.score(word)
    new(word).score
  end

  def score
    total_score = 0
    word.chars.each do |letter|
      total_score += value_of_letter.fetch(letter) {0}
    end
    total_score
  end

  def value_of_letter
    { "a" => 1, "b" => 3, "c" => 3, "d" => 2, "e" => 1, "f" => 4, "g" => 2,
    "h" => 4, "i" => 1, "j" => 8, "k" => 5, "l" => 1, "m" => 3, "n" => 1, "o" => 1, "p" => 3,
    "q" => 10, "r" => 1, "s" => 1, "t" => 1, "u" => 1, "v" => 4, "w" => 4, "x" => 8,
    "y" => 4, "z" => 10 }
  end

end
