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
    normalized_word != normalized_anagram_word && words_have_same_chars?(normalized_word, normalized_anagram_word)
  end

  def words_have_same_chars?(word1, word2)
    sorted_word_chars(word1) == sorted_word_chars(word2)
  end

  def sorted_word_chars(word)
    word.chars.sort
  end

  def normalize_word(word)
    word.downcase
  end

end
