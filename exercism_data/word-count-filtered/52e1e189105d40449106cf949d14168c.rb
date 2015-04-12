class Phrase
  attr_accessor :words

  def initialize(words)
    @words = words
  end

  def word_count
    list = Hash.new(0)
    words.downcase.scan(/\w+/) { |w| list[w] += 1 }
    list
  end

end
