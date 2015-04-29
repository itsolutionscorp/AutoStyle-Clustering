class Anagram
  attr_accessor :word

  def initialize(word)
    self.word = word
    self.word.extend AnagramEquals
  end

  def match(list)
    list.select do |word|
      self.word.anagram_of? word
    end
  end

  private
  module AnagramEquals
    def anagram_of?(other)
      split(//).sort == other.split(//).sort
    end
  end
end
