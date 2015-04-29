class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    normalized_words.each_with_object(Hash.new(0)) { |w, h| h[w] += 1 }
  end

  private

  def normalized_words
    @phrase.scan(/\w+/).map(&:downcase)
  end
end
