class Scrabble

  def initialize(word)
    if !word.nil?
      @word = word.downcase
    else
      @word = ""
    end

    @scores = {
      "a" => 1, "b" => 3, "c" => 3, "d" => 2, "e" => 1,
      "f" => 4, "g" => 2, "h" => 4, "i" => 1, "j" => 8,
      "k" => 5, "l" => 1, "m" => 3, "n" => 1, "o" => 1,
      "p" => 3, "q" => 10, "r" => 1, "s" => 1, "t" => 1,
      "u" => 1, "v" => 4, "w" => 4, "x" => 8, "y" => 4,
      "z" => 10
    }
    @scores.default = 0
  end

  def score
    score = 0
    @word.each_char do |letter|
      value = @scores[letter]
      score += value
    end

    return score
  end

  def self.score(word)
    Scrabble.new(word).score
  end

end
