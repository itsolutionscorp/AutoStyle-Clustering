# encoding: utf-8

# Anagram checker for exercism exercise
class Anagram
  def initialize(word)
    # the tests want us to check if the word is the same, too
    @downcased_word = word.downcase
    # we just need the letters in some known order
    @letters = letters_of(@downcased_word)
  end

  def match(words)
    words.select do |word|
      !same_word?(word) && same_letters?(word)
    end
  end

  private

  def same_word?(word)
    @downcased_word == word.downcase
  end

  def same_letters?(word)
    letters_of(word) == @letters
  end

  def letters_of(word)
    word.downcase.chars.sort
  end
end
