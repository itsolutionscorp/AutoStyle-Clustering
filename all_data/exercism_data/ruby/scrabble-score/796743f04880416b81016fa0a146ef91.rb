class Scrabble
  attr_reader :word

  def initialize(word)
    @word = (word || "").downcase
  end

  def score
    SCORES.inject(0)  do |total, (score, letters)|
      total + score * word.chars.count { |i| letters.include? word[i] }
    end
  end

  def self.score(word)
    Scrabble.new(word).score
  end

  SCORES = { 1 => ["a", "e", "i", "o", "u", "l", "n", "r", "s", "t"], 2 => ["d", "g"], 3 => ["b", "c", "m", "p"], 
    4 => ["f", "h", "v", "w", "y"], 5 => ["k"], 8 => ["j", "x"], 10 => ["q", "z"] }
end
