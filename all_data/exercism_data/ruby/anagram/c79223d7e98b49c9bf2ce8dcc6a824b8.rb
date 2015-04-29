# encoding: utf-8

# Anagram checker for exercism exercise
class Anagram
  def initialize(word)
    # the tests want us to check if the word is the same, too
    @word = word.downcase
    # we just need the letters in some known order
    @letters = letters_of(@word)
  end

  def match(words)
    words.select do |word|
      word.downcase != @word && letters_of(word.downcase) == @letters
    end
  end

  private

  def letters_of(word)
    word.split('').sort.join
  end
end
