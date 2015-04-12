class Phrase
  attr_accessor :text

  def initialize(text)
    @text = text
  end

  def normalized_text
    text.downcase
  end

  def words
    normalized_text.split(/[^a-z0-9]/).reject(&:empty?)
  end

  def word_count
    counts = Hash.new(0)
    words.each { |w| counts[w] += 1 }
    counts
  end
end
