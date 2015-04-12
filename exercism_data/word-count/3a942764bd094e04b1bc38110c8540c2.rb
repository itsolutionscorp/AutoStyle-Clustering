class Phrase
  attr_reader :words

  def initialize(words)
    @words = words
  end

  def word_count
    cleaned_words.each_with_object(Hash.new(0)) do |word, collection|
      collection[word] += 1
    end
  end

  private

  def cleaned_words
    words.downcase.scan(/[0-9a-zA-Z\']+/)
  end

end
