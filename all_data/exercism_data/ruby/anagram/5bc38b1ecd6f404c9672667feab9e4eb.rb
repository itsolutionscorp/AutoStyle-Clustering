class Anagram
  def initialize word
   @original_word = word
  end

  def match words
    words.inject([]) do |matching_anagrams,word|
      matching_anagrams << word if equal_when_sorted(@original_word, word)   && !words_equal?(word, @original_word)
      matching_anagrams
    end
  end

  private

  def equal_when_sorted(word1, word2)
    sorted(word1) == sorted(word2)
  end

  def sorted(word)
    word.downcase.split('').sort
  end

  def words_equal?(word1, word2)
    word1.downcase == word2.downcase
  end
end
