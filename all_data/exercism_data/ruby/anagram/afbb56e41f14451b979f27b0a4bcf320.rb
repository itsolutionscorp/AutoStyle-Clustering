class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(list)
    posibilities & list
  end

private

  def posibilities
    possible = permutations_of(@word) 
    possible.delete(@word) 
    possible.uniq
  end
 
  def permutations_of(word)
    word.chars.permutation.map(&:join)
  end
end
