class Anagram
  def initialize(word)
    @word = word
  end

  def match(possible_anagrams)
    needle = sorted_word(@word)
    possible_anagrams.select{|haystack| sorted_word(haystack) == needle }
  end

  private
  def sorted_word(word)
    word.downcase.chars.sort
  end
end
