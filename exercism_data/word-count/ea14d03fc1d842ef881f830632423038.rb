class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    word_counter = WordCounter.new unpunctuated.downcase

    word_counter.count_words
  end

  class WordCounter
    def initialize phrase
      @phrase = phrase
    end

    def count_words
      @phrase.split(" ").inject({}) do |memo, word|
        actual_count = memo[word].to_i
        memo[word] = actual_count + 1

        memo
      end
    end
  end

  private

  def unpunctuated
    @phrase.gsub(/[^0-9a-zA-Z ]/, ' ')
  end

end
