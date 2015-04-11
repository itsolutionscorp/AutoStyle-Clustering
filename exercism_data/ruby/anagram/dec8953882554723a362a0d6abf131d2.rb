class Anagram
  def initialize word
    @word = word
  end

  def match(possible_anagrams)
    possible_anagrams.select { |word|  anagram?(word) }
  end

  def anagram?(word)
    word.downcase != @word.downcase && sort(@word) == sort(word)
  end

  def sort(word)
    word.downcase.split("").sort.join
  end
end
