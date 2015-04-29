class Phrase
  attr_reader :words

  def initialize(words)
    @words = words
  end

  def word_count
    every_word.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  private

  def every_word
    words.downcase.scan(/[\w]+/)
  end
end
