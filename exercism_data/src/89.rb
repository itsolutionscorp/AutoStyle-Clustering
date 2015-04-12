class Phrase
  attr_reader :words

  def initialize(phrase)
    @words = phrase.downcase.scan(/[\w']+/)
  end

  def word_count
    data = Hash.new(0)
    words.each do |word|
      data[word] += 1
    end
    data
  end
end
