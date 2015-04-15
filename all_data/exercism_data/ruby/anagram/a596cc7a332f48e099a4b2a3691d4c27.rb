class Anagram
  def initialize(word)
    @word = word
  end

  def match(anagrams)
    Array(anagrams).find_all do |candidate|
      @word != normalize(candidate) && sort_word_chars(@word) == sort_word_chars(candidate)
    end
  end

  private

  def sort_word_chars(word)
    word.downcase.chars.sort
  end

  def normalize(word)
    word.downcase
  end
end
