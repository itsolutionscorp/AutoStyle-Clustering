require 'Set'

class Anagram
  def initialize word
    @base_letters = word_to_letters word
  end

  def match words
    words.select do |word|
      is_anagram? word
    end
  end

  private

  def is_anagram? word
    @base_letters == word_to_letters(word)
  end

  def word_to_letters word
    word.downcase.split('').sort
  end
end
