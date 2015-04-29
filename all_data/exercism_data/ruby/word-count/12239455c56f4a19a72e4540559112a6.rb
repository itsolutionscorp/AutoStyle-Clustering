class Phrase

  attr_reader :input

  def initialize(input)
    @input = input
  end

  def word_count
    words = make_words
    words.each_with_object(Hash.new(0)) do |word,counts|
      counts[word] +=1
    end
  end

  def make_words
    input.downcase.scan(/\w+/)
  end

end
