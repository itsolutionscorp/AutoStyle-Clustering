class Anagram
  def initialize(word)
    @word = word.downcase
    @sorted_word = sorted(@word)
  end

  def match(possible_anagrams)
    possible_anagrams.select { |word| anagram?(word.downcase) }
  end

  def anagram?(word)
    word != @word  and  sorted(word) == @sorted_word
  end

  def sorted(word)
    word.chars.sort
  end
end
