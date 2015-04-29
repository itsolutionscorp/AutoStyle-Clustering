class Scrabble

  def self.score(word)
    Scrabble.new(word).score
  end

  def initialize(word)
    @word = word || ""
  end

  def score
    score_hash.reduce(0) do |sum, (letter, count)|
      sum + count * value_of(letter)
    end
  end

private 

  def self.create_letter_values(values_array)
    values_array.each_with_object({}) do |(letters, value), hash|
      letters.chars.each { |l| hash[l] = value }
    end
  end

  VALUES = create_letter_values [["aeioulnrst", 1], 
                                 ["dg",         2], 
                                 ["bcmp",       3], 
                                 ["fhvwy",      4], 
                                 ["k",          5], 
                                 ["jx",         8], 
                                 ["qz",        10]]

  def score_hash
    @word.chars.each_with_object(Hash.new(0)) do |char, hash|
      hash[char.downcase] += 1
    end
  end

  def value_of(letter)
    VALUES[letter] || 0
  end

end
