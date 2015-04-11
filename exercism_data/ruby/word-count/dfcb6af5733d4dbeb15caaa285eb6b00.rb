class Phrase
  attr_reader :words

  def initialize(phrase)
    @words = phrase.scan(/\w+/)
  end

  def word_count
    words.each.with_object(Hash.new(0)) do |word, word_count|
      word_count[word.downcase] += 1
    end
  end
end
