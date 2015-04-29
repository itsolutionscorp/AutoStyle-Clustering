class Phrase

  attr_reader :word_count

  def initialize(phrase)
    normal_words = normalized_words(phrase)

    @word_count = summarize(normal_words)
  end

  private

  def normalized_words(phrase)
    downcased_phrase = phrase.downcase

    downcased_phrase.scan /[a-z0-9]+/
  end

  def summarize(words)
    words.each_with_object(Hash.new(0)) do |word, summary|
      summary[word] += 1
    end
  end
end
