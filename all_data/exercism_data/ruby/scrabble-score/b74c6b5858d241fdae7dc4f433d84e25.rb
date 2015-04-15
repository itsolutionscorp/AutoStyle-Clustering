class Scrabble

  LETTERS = {
    '1' => %w(A E I O U L N R S T),
    '2' => %w(D G),
    '3' => %w(B C M P),
    '4' => %w(F H V W Y),
    '5' => %w(K),
    '8' => %w(J X),
    '10' => %w(Q Z)
  }

  attr_reader :letters

  def initialize(letters)
    @letters = letters.to_s
  end
  
  def self.score(letters)
    new(letters).score
  end

  def score
    remove_whitespace.reduce(0) { |sum, letter| sum += LETTERS.select { |k,array| array.include?(letter.upcase) }.keys.reduce(:+).to_i }
  end

  def remove_whitespace
    letters.scan(/\w/)
  end

end
