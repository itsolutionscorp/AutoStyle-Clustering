class Phrase 

  attr_reader :words

  def initialize(words)
    @words = words
  end

  def word_count
    word_array.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

  def word_array
    words.downcase.scan(/\w+/)
  end

end
