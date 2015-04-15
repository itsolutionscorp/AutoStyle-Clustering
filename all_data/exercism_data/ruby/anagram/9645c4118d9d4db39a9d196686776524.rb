# encoding: utf-8

class Anagram
  def initialize(word)
    @subject = word.downcase
    @chars = lowercase_chars(@subject)
  end

  def match(candidates)
    candidates.select do |c|
       !same?(c) && anagram?(c)
    end
  end

  private

  def same?(word)
    @subject == word.downcase
  end

  def anagram?(word)
    word.size == @subject.size &&
      lowercase_chars(word).slice(0, @chars.size) == @chars
  end

  def lowercase_chars(word)
    word.downcase.chars.sort
  end
end
