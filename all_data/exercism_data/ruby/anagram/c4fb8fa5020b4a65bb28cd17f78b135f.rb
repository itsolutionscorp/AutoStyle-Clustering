class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select { |word| find_anagrams(word) }
  end

  def sort_word(word)
    word.downcase.chars.sort
  end

  def find_anagrams(word)
    sort_word(word) == sort_word(@word) && word.downcase != @word.downcase
  end
end
