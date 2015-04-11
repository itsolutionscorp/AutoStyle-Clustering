require 'prettyprint'
class Anagram
  def initialize(word)
    @word = String(word)
    @letters = @word.downcase.chars.to_a.sort
    @anagrams=[]
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
  def anagram?(check)
    return false unless check != @word
    check_chars = check.downcase.chars.to_a.sort
    check_chars == @letters
  end
end
