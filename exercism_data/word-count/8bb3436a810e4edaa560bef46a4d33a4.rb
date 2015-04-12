class Phrase
  attr_accessor :words

  def initialize(words)
    @words = words
  end

  def word_count
    word_list = Hash.new(0)
    words.downcase.scan(/\w+/) do |word|
      word_list[word] += 1
    end
    word_list
  end

end
