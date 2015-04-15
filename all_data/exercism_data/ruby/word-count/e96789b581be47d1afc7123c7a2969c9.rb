class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.counts
  end

  private

    attr_reader :phrase

    def to_words
      phrase.downcase.scan(/\w+/)
    end

    def words
      @words ||= WordCollection.new(to_words)
    end
end

class WordCollection 

  def initialize(words)
    @words = words
  end

  def counts
    frequencies_of words
  end

  private

    attr_reader :words

    def frequencies_of(words)
      words.reduce(Hash.new(0)) do |freqs, word|
        freqs[word] += 1
        freqs
      end
    end
end
