class Scrabble
  def self.score(word)
    self.new(word).score
  end

  def initialize(word)
    @letters = word.is_a?(String) ? word.chars : []
  end

  def score
    @letters.map{ |l| value(l) }.reduce(0, :+)
  end

  private

  SCORES = { 'a' => 1,  'b' => 3,  'c' => 3,  'd' => 2,
             'e' => 1,  'f' => 4,  'g' => 2,  'h' => 4,
             'i' => 1,  'j' => 8,  'k' => 5,  'l' => 1,
             'm' => 3,  'n' => 1,  'o' => 1,  'p' => 3,
             'q' => 10, 'r' => 1,  's' => 1,  't' => 1,
             'u' => 1,  'v' => 4,  'w' => 4,  'x' => 8,
             'y' => 4,  'z' => 10 }
  private_constant :SCORES

  def value(letter)
    SCORES[letter.downcase].to_i
  end
end