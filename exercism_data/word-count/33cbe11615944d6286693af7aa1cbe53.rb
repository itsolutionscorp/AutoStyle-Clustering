class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    parse_words.each_with_object(Hash.new(0)) do |word, words_with_count|
      words_with_count[word] += 1
    end
  end

  private

  def parse_words
    @text.scan(/\w+/).map(&:downcase)
  end
end
