# This is clearly not the most performant implementation,
# but I think it is a more readable one. :)
class Anagram

  def initialize(word)
    @permutations = word.chars.permutation
  end

  def match(words)
    words.select { |variation| anagram?(variation) }
  end

private

  def anagram?(word)
    @permutations.include?(word.chars)
  end

end
