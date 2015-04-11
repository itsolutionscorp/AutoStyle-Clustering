#!/usr/bin/env ruby

# Exercism 27
# Anagram

class Anagram

  def initialize(string)
    @string = string.downcase
    @anagrams = Array.new
  end

  def match(words)
    words.each { |x| x.downcase.chars.sort == @string.chars.sort && x.downcase.chars != @string.chars ? @anagrams << x : false }
    @anagrams
  end

end
