class Phrase
  def initialize(text)
    @text = text
  end

  def text_without_punctuation
    @text.gsub(/[^A-Za-z0-9 ]/, " ")
  end

  def sanitized_text
    text_without_punctuation.downcase
  end

  def word_count
    words = sanitized_text.scan(/\w+/)

    word_count = Hash.new(0)

    words.each_with_object(word_count) { |word, counts|
      counts[word] += 1
    }
  end
end
