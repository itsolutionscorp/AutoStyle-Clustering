class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    sanitized_words.each_with_object(Hash.new(0)) { |word, h| h[word] += 1 }
  end

  private

  def sanitized_words
    @phrase.downcase.scan(/([\w']+)/).flatten
  end
end
