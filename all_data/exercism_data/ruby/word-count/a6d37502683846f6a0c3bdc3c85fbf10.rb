class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count_words(split_phrase_into_words)
  end

  private

  def split_phrase_into_words
    @phrase.downcase.scan(/\w+/)
  end

  def count_words(words)
    results = Hash.new(0)
    words.each_with_object([]) { |word| results[word] += 1 }
    results
  end
end
