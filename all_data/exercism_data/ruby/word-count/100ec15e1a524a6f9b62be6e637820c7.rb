class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    frequencies_of(words).to_hash
  end

  private

    attr_reader :phrase

    def frequencies_of(words)
      WordFrequencies.new(words)
    end

    def words
      phrase.downcase.scan(/\w+/)
    end
end

class WordFrequencies 

  def initialize(words)
    @frequencies = calculate_frequencies_of(words)
  end

  def to_hash
    frequencies
  end

  private

    attr_reader :frequencies

    def calculate_frequencies_of(words)
      words.each_with_object(Hash.new(0)) { |word, freqs| freqs[word] += 1 }
    end
end
