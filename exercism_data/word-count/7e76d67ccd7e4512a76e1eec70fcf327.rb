class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    tally = {}
    normalized_words.each { |word| tally[word] = occurrences_of(word) }
    tally
  end

  private

  attr_reader :phrase

  def raw_words
    phrase.scan(/\w+/)
  end

  def normalized_words
    raw_words.map(&:downcase)
  end

  def occurrences_of(word)
    normalized_words.count(word)
  end
end
