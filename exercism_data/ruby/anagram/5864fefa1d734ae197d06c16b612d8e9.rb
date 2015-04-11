class Anagram
  def initialize(word)
    @word = word
  end

  def match(anagrams)
    Array(anagrams).find_all do |candidate|
      @word != normalize(candidate) && word_sort(@word) == word_sort(candidate)
    end
  end

  private

  def word_sort(word)
    word.downcase.chars.sort
  end

  def normalize(word)
    word.downcase
  end
end
