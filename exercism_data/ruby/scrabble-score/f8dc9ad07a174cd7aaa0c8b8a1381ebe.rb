class Scrabble

  def initialize(word)
    @word = parse(word)
  end

  def score
    @word.chars.map do |letter|
      letter_values[letter]
    end.inject(:+)
    # letters.inject(0) {|sum, score| sum + score}
  end

  def self.score(word)
    new(word).score
  end

  def letter_values
    { "A"=>1, "B"=>3, "C"=>3, "D"=>2,
      "E"=>1, "F"=>4, "G"=>2, "H"=>4,
      "I"=>1, "J"=>8, "K"=>5, "L"=>1,
      "M"=>3, "N"=>1, "O"=>1, "P"=>3,
      "Q"=>10, "R"=>1, "S"=>1, "T"=>1,
      "U"=>1, "V"=>4, "W"=>4, "X"=>8,
      "Y"=>4, "Z"=>10, "" => 0
    }
  end

  private

  def parse(word)
    word.to_s.strip.upcase
  end

end
