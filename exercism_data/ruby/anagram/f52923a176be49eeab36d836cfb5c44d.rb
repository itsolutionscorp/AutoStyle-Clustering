require 'set'

class Anagram

  def initialize(str)
    @word = to_word(str)
  end

  def match(strs_to_test)
    matches = strs_to_test.each_with_object(Set.new) do |str, matches|
      word = to_word(str)
      matches << word if anagram?(word)
    end
    matches.to_a
  end

  private

  def to_word(str)
    str.downcase
  end

  def anagram?(word_to_test)
    return false if @word == word_to_test
    sort_word(@word) == sort_word(word_to_test)
  end

  def sort_word(word)
    word.chars.sort.join
  end
end
