class Words
  def initialize(phrase)
    @phrase = phrase
  end

  def count
    normalized_words.uniq.inject({}) { |counts, word| counts.merge(word => occurrences_of(word)) }
  end

  private

  def normalized_words
    phrase_without_punctuation.split(" ").map(&:downcase)
  end

  # Returns the phrase with everything but letters, numbers and spaces removed
  def phrase_without_punctuation
    @phrase.gsub(/[^A-Za-z0-9 ]/, "")
  end

  def occurrences_of(word)
    normalized_words.count(word)
  end
end
