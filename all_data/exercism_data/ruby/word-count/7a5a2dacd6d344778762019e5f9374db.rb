class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    word_counter = WordCounter.new @phrase.downcase

    word_counter.count_words
  end

  class WordCounter
    def initialize phrase
      @phrase = phrase
    end

    def count_words
      splitted_words.each_with_object(Hash.new 0) do |word, memo|
        memo[word] += 1
      end
    end

    def splitted_words
      @phrase.scan(/[0-9a-zA-Z]+/)
    end
  end

end
