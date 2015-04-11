class Anagram
  def initialize original
    @original = AnagramWord.new original
  end

  def match candidates
    candidates.find_all { |candidate| original.anagram_of? candidate }
  end

  private

  attr_reader :original
end

class AnagramWord
  attr_reader :word
  def initialize word
    self.word = word
  end

  def anagram_of? candidate
    candidate.downcase != self.word && sorted_letters(candidate) == sorted_letters(self.word)
  end

  private

  def word= word
    @word = word.downcase
  end

  def sorted_letters word
    word.chars.sort
  end
end
