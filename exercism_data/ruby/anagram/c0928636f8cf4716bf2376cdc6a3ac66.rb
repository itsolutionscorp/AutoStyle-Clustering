class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(word_list)
    anagrams = []
    word_list.each do |word|
      anagrams << word if words_equal?(@word, word.downcase) && @word != word.downcase
    end
    anagrams
  end

  def words_equal?(word, word2)
    sort_word(word) == sort_word(word2)
  end

  def sort_word(word)
    word.chars.sort.join
  end


end
