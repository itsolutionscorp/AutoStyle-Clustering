class Anagram
  attr_reader :word
  def initialize word
    @word = word.downcase
  end

  def match words
    return words.select { |a_word|
      anagram_of?(a_word.downcase)
    }
  end

  protected
  def permutations
    return @permutations ||= word.split('').permutation.map(&:join).to_a
  end

  def anagram_of? other
    other != word && permutations.include?(other)
  end
end
