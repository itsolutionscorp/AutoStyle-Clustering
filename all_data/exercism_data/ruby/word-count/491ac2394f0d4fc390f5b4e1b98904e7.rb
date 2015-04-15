class Phrase
  attr_reader :words

  def initialize(text)
    @words = Words.new(text).to_a
  end

  def word_count
    {}.tap do |count|
      words.each do |word|
        count[normalize(word)] = count.fetch(normalize(word), 0) + 1
      end
    end
  end

  private

  def normalize(word)
    word.downcase
  end
end

class Words
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def to_a
    sanitized_words
  end

  private

  def sanitized_words
    words.collect { |word| to_alphanumeric(word) }
  end

  def words
    text.split(' ').select{ |str| word?(str) }
  end

  def word?(str)
    !to_alphanumeric(str).empty?
  end

  def to_alphanumeric(str)
    str.gsub(/[^a-zA-Z0-9]+/, '')
  end
end
