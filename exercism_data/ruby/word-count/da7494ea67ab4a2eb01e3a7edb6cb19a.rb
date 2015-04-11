class Phrase

  WORD_PATTERN = /[\w']+/

  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    words.each_with_object(Hash.new 0) do |word, counts|
      counts[word] += 1
    end
  end

  def words
    normalized_text.scan WORD_PATTERN
  end

  def normalized_text
    text.downcase
  end
end
