class Anagram
  def initialize(word)
    @word = word
  end

  def match(anagrams)
    anagrams.each_with_object([]) { |w, m| m << w if sort_chars(w) == sort_chars(@word) }
  end

  def sort_chars(word)
    word.downcase.chars.sort
  end
end
