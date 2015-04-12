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
    grouped_words = words.group_by{|word| word}

    grouped_words.each_with_object({}) do |(word, same_words), summary|
      summary[word] = same_words.length
    end
  end
end
