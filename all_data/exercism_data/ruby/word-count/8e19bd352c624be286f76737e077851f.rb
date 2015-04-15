class Phrase < String
  def word_count
    word_counter = WordCounter.new

    split(/\W+/).each do |word|
      word_counter.add word.downcase
    end

    word_counter.to_h
  end

  class WordCounter
    def initialize
      @count_store = Hash.new(0)
    end

    def add(word)
      @count_store[word] += 1
    end

    def to_h
      @count_store
    end
  end
end
