class Phrase
  def initialize text
    @text = text
  end

  def word_count
    counts = Hash.new(0)

    for word in text_in_words
      counts[word] += 1
    end

    counts
  end

  def text_in_words
    @text.downcase.scan /[\w']+/
  end

end
