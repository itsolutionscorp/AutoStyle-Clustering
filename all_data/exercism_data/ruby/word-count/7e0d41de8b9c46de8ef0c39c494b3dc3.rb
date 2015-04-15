class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @counts ||= count_words
  end

  private

    def count_words
      counts = Hash.new(0)
      splits.each do |word|
        key = key_for word
        counts[key] += 1
      end

      counts
    end

    def key_for(word)
      word.downcase
    end

    def splits
      sanitized.split(/\s+/)
    end

    def sanitized
      @phrase.gsub(/\W/, ' ')
    end
end
