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
    @word = word || ''
  end

  def score
    word.strip.chars.map{ |l| score_for_letter(l) }.reduce(:+) || 0
  end

  private

  def score_for_letter(letter)
    LETTER_SCORES.each do |letters,score| 
      break score if letters.include?(letter.upcase)
    end
  end

end
