# anagram.rb
class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def sort
    word.chars.sort
  end

  def match(possible_anagrams)
    possible_anagrams.select do |anagram|
      anagram_of? Anagram.new(anagram)
    end
  end

  def anagram_of?(anagram)
    word != anagram.word && sort == anagram.sort
  end
end
