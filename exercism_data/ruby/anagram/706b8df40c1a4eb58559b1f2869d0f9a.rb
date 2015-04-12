# encoding: utf-8

class Anagram
  def initialize(word)
    @word = word
  end

  def match(word_list)
    word_list.select do |word|
      same_chars = prepare_chars(word) == prepare_chars(@word)
      different = @word.casecmp(word) != 0
      same_chars && different
    end
  end

  private

  def prepare_chars(word)
    word.downcase.chars.sort.join
  end
end