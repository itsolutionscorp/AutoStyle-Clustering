#!/usr/bin/ruby
# Anagram exercise

class Anagram
  def initialize(word)
    @word = word.downcase
  end
  
  def match(wordlist)
    wordchars = @word.chars.sort
    
    matches = wordlist.each_with_object([]) do |test, list|
      ltest = test.downcase
      testchars = ltest.chars.sort
      list.push(test) if testchars == wordchars and ltest != @word
    end
  end
end
