class Words
  class Word < String
    def sanitized
      downcase.without_unwanted_characters
    end

    protected

    def without_unwanted_characters
      gsub(/\W/, '')
    end
  end

  def initialize(words)
    @words = sanitized(words)
  end

  def count
    Hash[@words.collect { |word|
      [word, count_word(word)]
    }]
  end

  private

  def count_word(word)
    @words.count { |w| w == word }
  end

  def sanitized(words)
    words.
      split.
      collect { |word| Word.new(word).sanitized }.
      reject(&:empty?)
  end
end
