class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    if !@word_count
      normal_words = normalized_words(@phrase)
      @word_count = summarize(normal_words)
    end

    @word_count
  end

  private

  def normalized_words(phrase)
    phrase.downcase.scan /[a-z0-9]+/
  end

  def summarize(words)
    words.each_with_object(Hash.new(0)) do |word, summary|
      summary[word] += 1
    end
  end
end
