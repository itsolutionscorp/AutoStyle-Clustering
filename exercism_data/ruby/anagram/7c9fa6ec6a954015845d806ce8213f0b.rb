class Anagram
  def initialize(anagram_word)
    @anagram_word = anagram_word
  end

  def match(words)
    words.select{|word| word_match?(word)}
  end

  private

  def word_match?(word)
    WordComparator.words_differ?(word, @anagram_word) &&
      WordComparator.words_have_same_chars?(word, @anagram_word)
  end

end


class WordComparator

  def words_have_same_chars?(word1, word2)
    sorted_word_chars(word1.downcase) == sorted_word_chars(word2.downcase)
  end

  def words_differ?(word1, word2)
    word1.downcase != word2.downcase
  end

  def self.words_have_same_chars?(word1, word2)
    new.words_have_same_chars?(word1, word2)
  end

  def self.words_differ?(word1, word2)
    new.words_differ?(word1, word2)
  end

  private

  def sorted_word_chars(word)
    word.chars.sort
  end

end
