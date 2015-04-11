class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    WordCounter.new(phrase).count
  end
end

class WordCounter
  SPLIT_PATTERN = /[^\p{Number}\p{Letter}]/

  attr_reader :words

  def initialize(phrase)
    @words = split_words(phrase.downcase)
  end

  def count
    @count ||= words.uniq.inject({}) do |word_counter, word|
      word_counter[word] = words.count(word)
      word_counter
    end
  end

  private

  def split_words(phrase)
    @words ||= phrase.split(SPLIT_PATTERN).delete_if(&:empty?)
  end
end
