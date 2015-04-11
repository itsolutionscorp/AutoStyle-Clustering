class Anagram
  def initialize original
    @original = original
  end

  def match candidates
    AnagramWord.new(original).find_anagrams candidates
  end

  private

  attr_reader :original
end

class AnagramWord
  attr_reader :word

  def initialize word
    @word = word.downcase
  end

  def find_anagrams candidates
    AnagramWords(candidates).find_all { |candidate| self.anagram? candidate }.map &:to_s
  end

  def to_s
    word
  end

  protected

  def anagram? candidate
    candidate.word != self.word && candidate.letters == self.letters
  end

  def letters
    word.chars.sort
  end
end

def AnagramWords words
  words.map { |word| AnagramWord.new word }
end
