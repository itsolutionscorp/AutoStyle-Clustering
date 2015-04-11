class Array
    def sum
        self.inject{|sum,x| sum + x }
    end
end

class Scrabble

  @@scores = {
    'A' => 1,
    'E' => 1,
    'I' => 1,
    'O' => 1,
    'U' => 1,
    'L' => 1,
    'N' => 1,
    'R' => 1,
    'S' => 1,
    'T' => 1,
    'D' => 2,
    'G' => 2,
    'B' => 3,
    'C' => 3,
    'M' => 3,
    'P' => 3,
    'F' => 4,
    'H' => 4,
    'V' => 4,
    'W' => 4,
    'Y' => 4,
    'K' => 5,
    'J' => 8,
    'X' => 8,
    'Q' => 10,
    'Z' => 10
    }
    @@scores.default = 0

  def initialize(w)
    if w.nil?
      @@word = ""
    else
      @@word = w
    end
  end

  def score
    a = @@word.chars.map { |c| @@scores[c.upcase] }
    if a.any?
      return a.inject(:+)
    else
      return 0
    end
  end

  def self.score(word)
    return Scrabble.new(word).score
  end

end
