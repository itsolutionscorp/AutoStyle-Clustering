class Scrabble
  attr_reader :word

  def scores
    {
      "a" => 1, "b" => 3, "c" => 3, "d" => 2, "e" => 1,
      "f" => 4, "g" => 2, "h" => 4, "i" => 1, "j" => 8,
      "k" => 5, "l" => 1, "m" => 3, "n" => 1, "o" => 1,
      "p" => 3, "q" => 10, "r" => 1, "s" => 1, "t" => 1,
      "u" => 1, "v" => 4, "w" => 4, "x" => 8, "y" => 4,
      "z" => 10
    }
  end

  def initialize(word)
    @word = clean(word)
  end

  def self.score(word)
    self.new(word).score
  end

  def score
    return 0 if word.empty?
    score = 0
    word.each_char do |letter|
      score += scores[letter]
    end
    score
  end

  private

  # I'm soo tempted to extend the string class but idk if I should :/
  def clean(word)
    word = "" if word == nil
    word = word.delete(" ").delete("\t").delete("\n")
    word.downcase
  end
end
