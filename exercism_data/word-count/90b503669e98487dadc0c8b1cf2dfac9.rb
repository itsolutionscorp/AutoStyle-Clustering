class Phrase < String
  def word_count
    word_counter = WordCounter.new

    reduce_words do |word|
      word_counter.add word
    end

    word_counter.to_h
  end

  def reduce_words(&block)
    split(/\W+/).each do |phrase_part|
      block.call Word.new(phrase_part)
    end
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

  class Word < String
    def initialize(object)
      super
      normalize_case
    end

    alias :normalize_case :downcase!
  end
end
