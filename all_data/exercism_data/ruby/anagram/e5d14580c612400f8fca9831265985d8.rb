class Anagram < String

  def match(words)
    words.select do |word|
      Histogram.new(self) == Histogram.new(word)
    end
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
