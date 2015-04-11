class Anagram
  attr_reader :base

  def initialize base
    @base = base
  end

  def match dictionary
    dictionary.select { |word| isAnagram? word }
  end

  private

  def isAnagram? word
    n_word = word.downcase
    n_base = base.downcase
    n_base != n_word && sorted(n_word) == sorted(n_base)
  end

  def sorted word
    word.chars.sort.join
  end
end
