class Phrase

  attr_reader :input

  def initialize(input)
    @input = input
  end

  def word_count
    formatted_input.each_with_object(Hash.new(0)) do |word, counter|
      counter[word] += 1
    end
  end

  def formatted_input
    input.downcase.scan(/\w+/)
  end

end
