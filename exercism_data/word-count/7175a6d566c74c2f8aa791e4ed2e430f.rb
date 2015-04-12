class Phrase < String
  def word_count
    word_counter = WordCounter.new

    reduce_words do |word|
      word_counter.add word
    end

    word_counter.to_hash
  end

  def reduce_words(&block)
    split(/\W+/).each do |phrase_part|
      block.call Word.new(phrase_part)
    end
  end

  alias :normalize_case :downcase

  class WordCounter
    def initialize
      @hash = Hash.new(0)
    end

    def add(word)
      @hash[word] += 1
    end

    def to_hash
      @hash
    end
  end

  class Word < String
    def initialize(object)
      object = object.normalize_case
      super
    end
  end
end
