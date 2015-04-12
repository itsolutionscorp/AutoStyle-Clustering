class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = phrase.split(/[^\w']+/).map(&:downcase)
    counts = Hash.new(0)

    words.each { |word| counts[word] += 1 }

    counts
  end
end
