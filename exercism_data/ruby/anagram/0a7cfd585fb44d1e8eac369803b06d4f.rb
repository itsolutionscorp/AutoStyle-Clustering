# Author::    Tim Henigan
# Copyright:: Copyright (c) 2013
# License::   MIT (http://opensource.org/licenses/MIT)

# Given a word and a list of possible anagrams, selects the correct one(s)

class Anagram

  def initialize(word)
    @word_to_match = word.downcase
    @sorted_word = @word_to_match.chars.sort.join
  end

  def match(words)
    words.each_with_object(Array.new) { |word, matches|
        next if word.downcase == @word_to_match
        matches << word if word.downcase.chars.sort.join == @sorted_word
    }
  end

end
