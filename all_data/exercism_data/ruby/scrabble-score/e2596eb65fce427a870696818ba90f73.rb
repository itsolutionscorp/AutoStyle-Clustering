class Scrabble

  def self.score(word)
    Scrabble.new(word).score
  end

  def initialize(word)
    @word = (word || "" ).gsub(/\W/, '').downcase
  end

  def score
    @word.each_char.map { |c| VALUES[c] }.reduce(&:+) || 0
  end

private 

  def self.create_letter_values(letter_values)
    letter_values.each_with_object(Hash.new(0)) do |(letters, value), hash|
      letters.each_char { |l| hash[l] = value }
    end
  end

  VALUES = create_letter_values [["aeioulnrst", 1], 
                                 ["dg",         2], 
                                 ["bcmp",       3], 
                                 ["fhvwy",      4], 
                                 ["k",          5], 
                                 ["jx",         8], 
                                 ["qz",        10]]

end
