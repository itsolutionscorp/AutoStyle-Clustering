class Phrase

  attr_reader :words
  
  def initialize(string)
    @words = string.split(/\W+/).map &:downcase
  end

  def hash_of_ints
    Hash.new { |h, k| h[k] = 0 }
  end

  def word_count
    hash_of_ints.tap do |hash|
      words.each { |word| hash[word] += 1 }
    end
  end

end
