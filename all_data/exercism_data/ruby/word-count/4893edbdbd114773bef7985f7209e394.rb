class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words.reduce({}) { |acc, word| acc.merge(word => 1, &sum) }
  end

  private

  def sum
    ->(_, x, y) { x + y }
  end

  def words
    text.downcase.scan(/[a-z0-9]+/)
  end

  attr_reader :text
end
