class Anagram
  def initialize(anagram_word)
    @anagram_word = anagram_word
  end

  def match(words)
    words.select{|word| word_match?(word)}
  end

  private

  def word_match?(word)
    normalized_anagram_word = normalize_word(@anagram_word)
    normalized_word = normalize_word(word)
    normalized_word != normalized_anagram_word &&
        WordComparator.words_have_same_chars?(normalized_word, normalized_anagram_word)
  end

  def normalize_word(word)
    word.downcase
  end

end


class WordComparator

  def words_have_same_chars?(word1, word2)
    sorted_word_chars(word1) == sorted_word_chars(word2)
  end

  def self.words_have_same_chars?(word1, word2)
    new.words_have_same_chars?(word1, word2)
  end

  private

  def sorted_word_chars(word)
    word.chars.sort
  end

end
