# anagram.rb
class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def sort(word)
    word.downcase.chars.sort
  end

  def match(possible_anagrams)
    possible_anagrams.select do |anagram|
      anagram.downcase != word.downcase &&
        sort(anagram) == sort(word)
    end
  end
end
