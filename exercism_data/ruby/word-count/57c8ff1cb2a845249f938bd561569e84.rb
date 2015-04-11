class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words.reduce({}) { |acc, word| acc.merge(word => acc.fetch(word, 0) + 1) }
  end

  private

  def words
    text.downcase.scan(/[a-z0-9]+/)
  end

  attr_reader :text
end
