class Phrase
  def initialize text
    @text = text
  end

  def word_count
    words = @text.downcase.scan /[\w']+/
    counts = Hash.new(0)

    for word in words
      counts[word] += 1
    end

    counts
  end
end
