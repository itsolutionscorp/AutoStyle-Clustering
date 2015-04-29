# encoding: utf-8

class Anagram
  def initialize(word)
    @word = word
    @chars = lowercase_chars(@word)
  end

  def match(candidates)
    candidates.select do |c|
       !same?(c) && anagram?(c)
    end
  end

  private

  def same?(word)
    @word.downcase == word.downcase
  end

  def anagram?(word)
    word.size == @word.size &&
      lowercase_chars(word).slice(0, @chars.size) == @chars
  end

  def lowercase_chars(word)
    word.downcase.chars.sort
  end
end
