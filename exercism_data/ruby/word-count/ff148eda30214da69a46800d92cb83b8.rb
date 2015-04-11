class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    normalized_words.each_with_object Hash.new(0) do |key, hash|
      hash[key] += 1
    end
  end

  private
  attr_reader :text

  def normalized_words
    text.downcase.split(/\W+/)
  end
end
