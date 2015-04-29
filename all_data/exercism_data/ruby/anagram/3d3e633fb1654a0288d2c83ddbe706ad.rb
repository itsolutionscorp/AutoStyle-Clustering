require 'prettyprint'
class Anagram
  def initialize(word)
    @word = String(word)
    @letters = @word.downcase.chars.to_a.sort
    @anagrams=[]
  end
  def match(words)
    words.each do |check|
      next if check.downcase == @word.downcase;
      if is_anagram?(check)
        @anagrams.push(check)
      end
    end
    @anagrams
  end
  def is_anagram?(check)
    check_chars = check.downcase.chars.to_a.sort
    check_chars == @letters
  end
end
