class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    tally = {}
    words.each { |word| tally[word] = occurrences_of(word) }
    tally
  end

  private

  def words
    raw_words = phrase.scan(/\w+/)
    raw_words.map(&:downcase)
  end

  def occurrences_of(word)
    words.count(word)
  end
end
