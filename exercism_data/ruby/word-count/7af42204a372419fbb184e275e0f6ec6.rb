class Phrase
  attr_reader :words

  def initialize(text)
    @words = Words.new(text.downcase)
  end

  def word_count
    Hash.new(0).tap do |count|
      words.each { |word| count[word] += 1 }
    end
  end
end

class Words
  include Enumerable

  attr_reader :text

  def initialize(text)
    @text = text
  end

  def each
    sanitized_words.each { |word| yield word }
  end

  private

  def sanitized_words
    words.collect { |word| to_alphanumeric(word) }
  end

  def words
    text.split.select{ |str| word?(str) }
  end

  def word?(str)
    !to_alphanumeric(str).empty?
  end

  def to_alphanumeric(str)
    str.gsub(/[^a-zA-Z0-9]+/, '')
  end
end
