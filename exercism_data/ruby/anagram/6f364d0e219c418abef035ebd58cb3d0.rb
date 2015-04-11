class Anagram < String

  def match(words)
    words.select do |word|
      anagram? word
    end
  end

  def anagram?(word)
    histogram == Histogram.new(word)
  end

  def histogram
    @histogram ||= Histogram.new(self)
  end

  class Histogram < Hash
    def initialize(word)
      super 0
      word.each_char do |char|
        self[char] += 1
      end
    end
  end
end
