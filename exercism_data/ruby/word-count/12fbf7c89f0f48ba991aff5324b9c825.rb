class Phrase
  attr_accessor :words

  def initialize(words)
    @words = words
  end

  def word_count
    list = Hash.new(0)
    each_word { |word| list[word] += 1 }
    list
  end

private
  def each_word
    words.downcase.scan(/\w+/) do |word|
      yield word
    end
  end

end
