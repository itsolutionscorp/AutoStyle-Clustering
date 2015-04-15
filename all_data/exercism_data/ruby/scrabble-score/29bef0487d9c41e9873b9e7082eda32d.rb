class Scrabble
  attr_reader :score, :word
  def initialize word
    if word == nil
      @word = nil
      @score = 0
    else
      @word = word.downcase
      @score = @word.scan(/[aeioulnrst]/).join.length 
      @score += @word.scan(/[dg]/).join.length*2 
      @score += @word.scan(/[bcmp]/).join.length*3 
      @score += @word.scan(/[fhvwy]/).join.length*4 
      @score += @word.scan(/[k]/).join.length*5 
      @score += @word.scan(/[jx]/).join.length*8 
      @score += @word.scan(/[qz]/).join.length*10
    end
  end
  
  def self.score word
    Scrabble.new(word).score
  end
end
