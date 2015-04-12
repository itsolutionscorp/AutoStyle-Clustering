class Phrase

  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    counts = Hash.new { 0 }
    words.each { |word| counts[word] += 1 }

    counts
  end

  def words
    normalized_text.scan word_regex
  end

  def normalized_text
    text.downcase
  end

  def word_regex
    /[\w']+/
  end
end
