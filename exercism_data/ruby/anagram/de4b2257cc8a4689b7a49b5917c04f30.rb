class Anagram
  attr_reader :word
  def initialize(word)
    @word = word
  end

  def match(possible_matches)
    possible_matches.select do |match|
      word_permutations.include?(match)
    end
  end

  def word_permutations
    @permutations ||= begin
      word.downcase.chars.permutation(word.length).map(&:join)
    end
  end
end
