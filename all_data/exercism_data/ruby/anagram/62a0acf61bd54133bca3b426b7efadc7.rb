class Anagram
  attr_accessor :word

  def initialize(word)
    self.word = word
    self.word.extend AnagramEquals
  end

  def match(list)
    list.select do |word|
      self.word.anagram_equals? word
    end
  end

  private
  module AnagramEquals
    def anagram_equals?(other)
      split(//).sort == other.split(//).sort
    end
  end
end
