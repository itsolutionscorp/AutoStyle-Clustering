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
    words.each { |word| yield word }
  end

  private

  def words
    text.split(/[^a-zA-Z0-9]+/)
  end
end
