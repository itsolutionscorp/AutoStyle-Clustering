class Phrase
  attr_reader :words

  def initialize(text)
    @words = text.downcase.split(/\W+/)
  end

  def word_count
    words.reduce Hash.new(0) do |acc, word|
      acc[word] += 1
      acc
    end
  end
end
