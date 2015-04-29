class Scrabble

  LETTERS = {
    %w(A E I O U L N R S T) => 1,
    %w(D G) => 2,
    %w(B C M P) => 3,
    %w(F H V W Y) => 4,
    %w(K) => 5,
    %w(J X) => 8,
    %w(Q Z) => 10
  }

  attr_reader :letters

  def initialize(letters)
    @letters = letters.to_s
  end
  
  def self.score(letters)
    new(letters).score
  end

  def score
    remove_whitespace.reduce(0) do |sum, letter| 
      sum += find_letter_value { letter }
    end
  end

  private
  def remove_whitespace
    letters.scan(/\w/)
  end

  def return_letter_value
    LETTERS.select { |array,score| score.to_i if array.include?(yield.upcase) }.values.first
  end

end
