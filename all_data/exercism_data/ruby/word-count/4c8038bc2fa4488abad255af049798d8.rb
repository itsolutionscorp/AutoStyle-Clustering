class Phrase < Hash
  SEPARATOR = /[\s,]/i
  def initialize(text)
    text.split(SEPARATOR).map do |t|
      word = Word.new(t)
      if word.valid?
        self[word.to_s] = self.fetch(word.to_s, 0) + 1
      end
    end
  end

  def word_count
    self
  end

  class Word
    VALID = /\w+/i
    def initialize(text)
      @content = text.slice(VALID)
    end

    def valid?
      !@content.nil? && !@content.empty?
    end

    def to_s
      @content.downcase
    end
  end
end
