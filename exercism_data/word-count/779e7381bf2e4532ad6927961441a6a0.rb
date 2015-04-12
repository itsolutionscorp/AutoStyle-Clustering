class Phrase
  attr_accessor :phrase, :words

  def initialize(phrase)
    @phrase = phrase
    @words = phrase.downcase.split(/[^[:word:]']+/)
  end

  def word_count
    word_counts = Hash.new(0)

    for word in words
      word_counts[word] += 1
    end

    word_counts
  end
end
