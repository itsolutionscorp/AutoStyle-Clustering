class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    Histogram.for(words)
  end

  private
  def words
    @text.split(/\W/).reject(&:empty?).map(&:downcase)
  end

  class Histogram
    def self.for(tokens)
      new(tokens).to_hash
    end

    def initialize(tokens)
      tokens.each { |token| count(token) }
    end

    def to_hash
      counts.dup
    end

    private
    def count(token)
      counts[token] += 1
    end

    def counts
      @counts ||= Hash.new(0)
    end
  end
end
