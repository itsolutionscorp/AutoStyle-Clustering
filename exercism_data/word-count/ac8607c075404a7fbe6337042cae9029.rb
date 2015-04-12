class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    sanitized_words.each_with_object(Hash.new(0)) { |word, h| h[word] += 1 }
  end

  private

  def sanitized_words
    @words.downcase.scan(/([\w']+)/).flatten
  end
end
