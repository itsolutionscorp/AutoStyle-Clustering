class Scrabble

  def initialize word
    @word = word ? word.strip : ''
  end

  SCORE = {
     1 => %w(A E I O U L N R S T),
     2 => %w(D G),
     3 => %w(B C M P),
     4 => %w(F H V W Y),
     5 => %w(K),
     8 => %w(J X),
    10 => %w(Q Z)
  }
  def SCORE.for letter
    select{|score, letters| letters.include? letter.upcase}.keys.first
  end

  def score
    @word.split('').reduce(0) do |score, letter|
      score + SCORE.for(letter)
    end
  end

  def self.score word
    new(word).score
  end
end
