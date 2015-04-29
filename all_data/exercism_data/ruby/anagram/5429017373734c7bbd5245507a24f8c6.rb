# encoding: UTF-8
# The Anagram class is initialized with a word. It then provides a match
# method that takes a list of words and returns those words that are
# anagrams of the original word.
class Anagram

  def initialize(word)
    @word = word.to_s
    @word_signature = word_signature(@word)
  end

  def match(word_list)
    word_list.select { |word| is_anagram?(word) }
  end

  private

  def is_anagram?(candidate)
    same_signature?(candidate) && !same_word?(candidate)
  end

  def same_signature?(candidate)
    word_signature(candidate) == @word_signature
  end

  def same_word?(candidate)
    candidate.casecmp(@word).zero?
  end

  def word_signature(word)
    word.downcase.chars.sort.join
  end

end
