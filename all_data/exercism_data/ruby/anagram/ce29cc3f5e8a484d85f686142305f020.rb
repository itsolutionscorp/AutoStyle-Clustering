# encoding: utf-8

class Anagram
  def initialize(word)
    @word = word
  end

  def match(word_list)
    word_list.select { |word| have_same_chars?(word) && @word.casecmp(word) != 0 }
  end

  private

  def have_same_chars?(word)
    prepare_chars(word) == prepare_chars(@word)
  end

  def prepare_chars(word)
    word.downcase.chars.sort.join
  end

  def identical?(word, other_word)
    word.downcase == other_word.downcase
  end
end
