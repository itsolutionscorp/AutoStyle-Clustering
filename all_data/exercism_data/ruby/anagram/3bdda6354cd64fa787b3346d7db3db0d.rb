class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(word_list)
    anagrams = []
    word_list.each do |word|
      anagrams << word if sort_word(word.downcase) == sort_word(@word) && @word != word.downcase
    end
    anagrams
  end

  def sort_word(word)
    word.chars.sort.join
  end
end
