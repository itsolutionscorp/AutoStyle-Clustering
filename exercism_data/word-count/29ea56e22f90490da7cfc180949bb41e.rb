require 'delegate'

class Phrase

  class NormalizableString < SimpleDelegator
    PUNCTUATION_TO_STRIP = /[^\w]/

    def strip_punctuation
      self.class.new(gsub(PUNCTUATION_TO_STRIP, ' '))
    end

    def normalize_case
      self.class.new(downcase)
    end

    def normalize
      normalize_case.strip_punctuation
    end
  end

  attr_reader :text
  private :text

  def initialize(text)
    @text = text
  end

  def word_count
    words.inject({}) do |wc, word|
      wc.merge word => words.count(word)
    end
  end

  private

  def words
    @words ||= normalized_text.split
  end

  def normalized_text
    NormalizableString.new(text).normalize
  end
end
