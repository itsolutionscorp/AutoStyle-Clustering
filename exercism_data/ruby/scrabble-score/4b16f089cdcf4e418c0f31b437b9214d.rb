class Scrabble
  
  attr_reader :word

  LETTER_SCORES = {
    %w(A E I O U L N R S T) => 1, 
    %w(D G) => 2, 
    %w(B C M P) => 3, 
    %w(F H V W Y) => 4, 
    %w(K) => 5, 
    %w(J X) => 8, 
    %w(Q Z) => 10
  }

  class << self
    def score(word)
      Scrabble.new(word).score
    end
  end

  def initialize(word)
    @word = word
  end

  def score
    return 0 if word.nil? || word.empty?
    word.chars.map{ |l| score_for_letter(l) }.reduce(:+) 
  end

  private

  def score_for_letter(letter)
    LETTER_SCORES.each do |letters,score| 
      return score if letters.include?(letter.upcase)
    end  
    0
  end

end
