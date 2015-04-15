class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    parse_words.each_with_object({}) do |word, words_with_count|
      words_with_count[word] ||= 0
      words_with_count[word] += 1
    end
  end

  private

  def parse_words
    @text.downcase.scan(/\w+/)
  end
end
