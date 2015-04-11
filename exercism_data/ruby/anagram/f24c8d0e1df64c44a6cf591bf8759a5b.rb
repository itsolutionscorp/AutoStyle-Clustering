class Anagram
  def initialize(word)
    @word = word
  end

  def match(possible_anagrams)
    possible_anagrams & actual_anagrams
  end

  private
  def actual_anagrams
    @word.downcase.split('').permutation.map(&:join)
  end
end
