class Anagram
  def initialize(word)
    @word = word
  end

  def match(possible_anagrams)
    possible_anagrams.find_all do |anagram|
      permutations.include?(anagram.downcase)
    end
  end

  def permutations
    @_permutations ||= @word.downcase.chars.permutation.map(&:join) - [@word]
  end
end
