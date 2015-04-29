class Phrase

  attr_reader :word_count

  def initialize(phrase)
    @word_count = summarize(phrase)
  end

  private

  def summarize(phrase)
    downcased_phrase = phrase.downcase

    words = downcased_phrase.scan /[a-z0-9]+/

    words.each_with_object({}) do |word, summary|
      summary[word] = words.count(word)
    end
  end
end
