class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    normalized_words.each_with_object({}) do |word, word_count|
      word_count[word] = (word_count[word] || 0) + 1
    end
  end

  private

  def normalized_words
    @phrase.downcase.split(/\W+/)
  end

end
