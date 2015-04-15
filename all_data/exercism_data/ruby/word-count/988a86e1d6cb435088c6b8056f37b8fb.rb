class Phrase

  def initialize(input)
    @words = input
  end

  def word_count
    counts = WordCounter.new(@words)
    counts.hash
  end

  class WordCounter
    def initialize(input)
      @input = input
    end

    def hash
      count
    end

    def count
      get_words.each_with_object(Hash.new(0)) do |word,hash|
        hash[word.downcase]+=1
      end
    end

    def get_words
      @input.scan(/\w+/)
    end
  end
end
