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
  PUCTUATION = /\W/

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def words
    @words ||= downcase_words(split_to_words(remove_puctuation(phrase)))
  end

  def occurrences
    words.inject(Hash.new(0)) do |occurrences, word|
      occurrences[word] += 1
      occurrences
    end
  end

  private

  def downcase_words(words)
    words.map(&:downcase)
  end

  def split_to_words(phrase)
    phrase.split(' ')
  end

  def remove_puctuation(phrase)
    phrase.gsub(PUCTUATION, ' ')
  end
end
