class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(word_list)
    anagrams = []
    word_list.each do |word.downcase|
      anagrams << word if sort_word(word) == sort_word(@word) && @word != word
    end
    anagrams
  end

  def sort_word(word)
    word.chars.sort.join
  end
end
