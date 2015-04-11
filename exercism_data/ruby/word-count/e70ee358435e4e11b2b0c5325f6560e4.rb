class Phrase
  def initialize(phrase)
    @words = extract_words(phrase)
  end

  def word_count
    @words.inject(Hash.new(0)) do |counts, word| 
      counts[word] += 1
      counts
    end
  end

  private
    def extract_words(phrase)
      normalize(phrase).split
    end

    def normalize(phrase)
      phrase.downcase
            .gsub(/\W/, ' ')
            .squeeze(' ')
    end
end
