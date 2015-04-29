class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    parse(phrase)
  end

  private

  def parse(phrase)
    words = prepare(phrase)
    count_ocurrences(words)
  end

  def prepare(phrase)
    phrase.downcase.scan(/\w+/)
  end

  def count_ocurrences(words)
    counts = Hash.new(0)
    words.each { |w| counts[w] += 1 }
    counts
  end
end
