class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    normalized_words.reduce Hash.new(0), &method(:increment)
  end

  private
  attr_reader :text

  def normalized_words
    text.downcase.split(/\W+/)
  end

  def increment(hash, key)
    hash[key] += 1
    hash
  end
end
