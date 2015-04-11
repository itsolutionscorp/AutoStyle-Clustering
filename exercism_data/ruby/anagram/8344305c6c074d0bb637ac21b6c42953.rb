require 'pry'

class Anagram
  def initialize word
    @word = word.downcase
  end

  def match words
    @words = words

    remove_identical_words
    match_each_word
  end

  private

  def match_each_word
    @words.each_with_object([]) do |word, anagrams|
      anagrams << word if normalize_word(word) == normalize_word(@word)
    end
  end

  def normalize_word word
    word.downcase.chars.sort.join
  end

  def remove_identical_words
    @words.delete_if { |w| w.downcase == @word }
  end

end
