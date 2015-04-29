class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(candidates)
    posibilities & candidates
  end

private

  def posibilities
    possible_anagrams = permutations_of(@word) 
    possible_anagrams.delete(@word) 
    possible_anagrams.uniq
  end
 
  def permutations_of(word)
    word.chars.permutation.map(&:join)
  end
end
