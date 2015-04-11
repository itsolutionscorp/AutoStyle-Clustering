class Scrabble
  attr_accessor :score, :word

  def initialize(word)
    @word = "#{word}".upcase
  end

  def score
    chars_to_score.map {|c| c.to_i }.inject(:+).to_i
  end

  def self.score(word)
    new(word).score
  end

  def chars_to_score
    word.chars.map! do |c|
      c.gsub!(/[AEIOULNRST]/, '1')
      c.gsub!(/[DG]/, '2')
      c.gsub!(/[BCMP]/, '3')
      c.gsub!(/[FHVWY]/, '4')
      c.gsub!(/[K]/, '5')
      c.gsub!(/[JX]/, '8')
      c.gsub!(/[QZ]/, '10')
      c
    end
  end

end
