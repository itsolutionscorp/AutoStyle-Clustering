class Phrase
  def initialize words
    @phrase= Words.new words
  end

  def word_count
    @phrase.normalize_case.ignore_punctuation.count_ocorrences
  end

  private

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

    def count_ocorrences
      @words.split(' ').each_with_object Hash.new(0) do |word, result|
        result[word] += 1
      end
    end
  end
end
