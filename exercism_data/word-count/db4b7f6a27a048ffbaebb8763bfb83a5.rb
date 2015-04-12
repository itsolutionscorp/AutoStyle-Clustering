class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new(0)
    normalized_words.each do |word|
      counts[word] += 1
    end

    counts
  end

  private

  def normalized_words
    @phrase.scan(/\w+/).map(&:downcase)
  end
end
