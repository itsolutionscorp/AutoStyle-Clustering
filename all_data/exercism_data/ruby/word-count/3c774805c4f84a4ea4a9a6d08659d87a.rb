class Phrase
  def initialize(raw_words)
    @raw_words = raw_words
  end

  def word_count
    @word_count ||= count_words
  end

  private

  def count_words
    counts = Hash.new(0)
    @raw_words.downcase.split(/\W+/).each do |word|
      counts[word] += 1
    end
    counts
  end
end
