class Scrabble
  
  LETTER_VALUES = { 
   1  => %W[A E I O U L N R S T],
   2  => %W[D G],
   3  => %W[B C M P],
   4  => %W[F H V W Y],
   5  => %W[K],
   8  => %W[J X],
   10 => %W[Q Z]
  }.each.with_object(Hash.new(0)) do |(value, letters), letter_values|
    letters.each { |letter| letter_values[letter] = value }
  end
  
  def self.score word
    new(word).score
  end
  
  def initialize(word)
    @word = String(word).upcase
  end
  
  def score
    @word.chars.each.inject(0) do |total, letter|
      total +=  LETTER_VALUES[letter]
    end
  end
  
end
