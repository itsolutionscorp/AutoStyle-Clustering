class Scrabble
  
  SCORES = { 
   1 => %W[A E I O U L N R S T],
   2 => %W[D G],
   3 => %W[B C M P],
   4 => %W[F H V W Y],
   5 => %W[K],
   8 => %W[J X],
   10 => %W[Q Z]
  }.invert
  
  def self.score word
    new(word).score
  end
  
  def initialize(word)
    @word = String(word).strip.upcase
  end
  
  def score
    @word.chars.each.inject(0) do |total, letter|
      score_for_letter = SCORES.select { |group, score| group.include? letter }.values.first
      total += score_for_letter
    end
  end
  
end
