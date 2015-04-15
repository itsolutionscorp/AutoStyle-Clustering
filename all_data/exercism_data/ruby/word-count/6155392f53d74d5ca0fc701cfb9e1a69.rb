class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def normalized
    text.downcase
  end

  def words
    normalized.split(/\W+/)
  end

  def word_count
    counts = Hash.new(0)
    words.each { |w| counts[w] += 1 }
    counts
  end
end
