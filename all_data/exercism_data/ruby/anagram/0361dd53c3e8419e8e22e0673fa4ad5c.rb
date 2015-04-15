class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(word_list)
    word_list.map(&:downcase).select { |word| words_equal?(@word, word) && @word != word}
  end

  def words_equal?(word, word2)
    sort_word(word) == sort_word(word2)
  end

  def sort_word(word)
    word.chars.sort.join
  end
end
