# encoding: utf-8

class Anagram
  def initialize(word)
    @subject = word.downcase
    @subject_fingerprint = fingerprint(@subject)
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
    fingerprint(word) == @subject_fingerprint
  end

  def fingerprint(word)
    word.downcase.chars.each_with_object(Hash.new(0)) do |char, hist|
      hist[char] += 1
    end
  end
end
