class Anagram
  def initialize(original_word)
    @word = original_word
  end

  def match(possible_anagrams)
    possible_anagrams.select { |possible_word| matching?(possible_word) }
  end

  def matching?(word_to_match)
    anagram?(word_to_match) && !words_the_same?(word_to_match)
  end

  def anagram?(word)
    normalize(word) == normalize(@word)
  end

  def words_the_same?(word_to_test)
    word_to_test.downcase == @word.downcase
  end

  def normalize(ugly)
    ugly.downcase.chars.sort
  end
end
