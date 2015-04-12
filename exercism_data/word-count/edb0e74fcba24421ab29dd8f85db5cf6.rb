class Phrase
  attr_reader :words

  def initialize(words)
    @words = words
  end

  def word_count
    words_and_occurences = Hash.new(0)
    words.downcase.scan(/[\w]+/) do |word|
      words_and_occurences[word] += 1
    end
    words_and_occurences
  end
end
