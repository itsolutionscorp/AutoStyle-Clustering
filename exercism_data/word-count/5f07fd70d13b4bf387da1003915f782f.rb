class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = parse_words(phrase)
    words.inject(Hash.new(0)) { |counts, word|
      counts[word] += 1
      counts
    }
  end

  private

  def parse_words(phrase)
    phrase.scan(/\w+/).map(&:downcase)
  end
end
