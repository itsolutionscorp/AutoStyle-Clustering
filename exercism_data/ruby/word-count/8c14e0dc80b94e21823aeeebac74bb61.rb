class Words
  def initialize(phrase)
    @phrase = phrase
  end

  def count
    normalized_words.uniq.inject({}) { |counts, word| counts.merge(word => occurrences_of(word)) }
  end

  private

  def normalized_words
    @phrase.downcase.split(/[^[:alnum:]]/).reject(&:empty?)
  end

  def occurrences_of(word)
    normalized_words.count(word)
  end
end
