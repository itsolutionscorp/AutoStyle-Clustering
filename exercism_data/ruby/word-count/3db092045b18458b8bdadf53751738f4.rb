class Phrase

  attr_reader :word

  def initialize(input)
    @word = input
  end

  def word_count
    formatted_word.each_with_object(Hash.new(0)) do |word, counter|
      counter[word] += 1
    end
  end

  def formatted_word
    word.downcase.scan(/\w+/)
  end

end
