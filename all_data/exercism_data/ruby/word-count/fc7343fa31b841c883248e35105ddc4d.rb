class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count_ocurrences(prepare)
  end

  private

  def prepare
    phrase.downcase.scan(/\w+/)
  end

  def count_ocurrences(words)
    counts = Hash.new(0)
    words.each { |w| counts[w] += 1 }
    counts
  end
end
