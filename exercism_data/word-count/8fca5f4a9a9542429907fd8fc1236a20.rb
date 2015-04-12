class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= WordOccurrencesInPhrase.new(phrase).occurrences
  end
end

class WordOccurrencesInPhrase
  PUCTUATION = /\W+/

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def words
    @words ||= downcase(words_without_punctuation(phrase))
  end

  def occurrences
    words.each_with_object(Hash.new(0)) do |word, occurrences|
      occurrences[word] += 1
    end
  end

  private

  def downcase(words)
    words.map(&:downcase)
  end

  def words_without_punctuation(phrase)
    phrase.split(PUCTUATION)
  end
end
