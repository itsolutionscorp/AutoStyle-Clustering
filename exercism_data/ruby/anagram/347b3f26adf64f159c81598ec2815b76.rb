class Anagram
  def initialize(word)
    @word = word.downcase       
  end

  def match(word_list)
    word_list.map(&:downcase).select { |word| @word != word && sorted_words_equal?(@word, word) }
  end

  def sorted_words_equal?(word, word2)
    sort_word(word) == sort_word(word2)
  end

  def sort_word(word)
    word.chars.sort.join
  end
end
