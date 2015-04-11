require 'prettyprint'
class Anagram
  def initialize(word)
    @word = String(word)
    @letters = @word.downcase.split("").sort
    @anagrams = []
  end
  def match(words)
    words.each do |word|
      next if word.downcase == @word.downcase;
      if anagram?(word)
        @anagrams.push(word)
      end
    end
    @anagrams
  end
  def anagram?(string_to_test)
    return false unless string_to_test != @word
    test_chars = string_to_test.downcase.split("").sort
    test_chars == @letters
  end
end
