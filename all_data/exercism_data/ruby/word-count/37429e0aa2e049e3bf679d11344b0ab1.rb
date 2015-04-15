class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= count_words
  end

  private

  def count_words
    counts = Hash.new(0)
    @phrase.split(/[^'\p{L}\d]+/).each do |word|
      counts[word.downcase] += 1
    end
    counts
  end

end
