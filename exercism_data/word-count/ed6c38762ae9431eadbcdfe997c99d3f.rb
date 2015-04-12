class Phrase < String
  def word_count
    split.each_with_object(WordCounter.new) do |word, word_counter|
      word_counter.add Word.new(word)
      word_counter
    end.to_hash
  end

  def remove_punctuation
    gsub(/\W/, "")
  end

  alias :normalize :downcase

  class WordCounter
    def initialize
      @hash = Hash.new(0)
    end

    def add(word)
      return if word.empty?
      @hash[word] += 1
    end

    def to_hash
      @hash
    end
  end

  class Word < String
    def initialize(object)
      object = object.normalize.remove_punctuation
      super
    end
  end
end
