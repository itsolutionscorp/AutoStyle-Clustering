class Phrase
  def initialize words
    @phrase= Words.new words
  end

  def word_count
    @phrase.normalize_case.ignore_punctuation.words.inject({}) do |result, word|
      result[word] = occurrences result[word]
      result
    end
  end

  private

  def occurrences count
    count.nil? ? 1 : count+1
  end

  class Words
    def initialize words
      @words = words
    end

    def normalize_case
      @words.downcase!
      self
    end

    def ignore_punctuation
      @words.gsub! /\W/, ' '
      self
    end

    def words
      @words.split ' '
    end
  end
end
