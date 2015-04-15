class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    unique_words.each_with_object(Hash.new(0)) { |word, counts|
      counts[word] += 1
    }
  end

  private

  def unique_words
    text.downcase.scan(/\w+/)
  end
end
