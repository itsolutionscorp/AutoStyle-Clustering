class Anagram
  attr_reader :word
  def initialize word
    @word = word.downcase
  end

  def match words
    words.select { |a_word|
      anagram_of?(a_word.downcase)
    }
  end

  protected
  def permutations
    @permutations ||= word.split('').permutation.map(&:join).to_a
  end

  def anagram_of? other
    other != word && permutations.include?(other)
  end
end
