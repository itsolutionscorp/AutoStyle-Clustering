class Phrase
  attr_accessor :words

  def initialize(words)
    @words = words
  end

  def word_count
    word_count = Hash.new(0)
    @words.downcase.scan(/[\w']+/) do |word|
      word_count[word] += 1
    end
    word_count
  end
end
