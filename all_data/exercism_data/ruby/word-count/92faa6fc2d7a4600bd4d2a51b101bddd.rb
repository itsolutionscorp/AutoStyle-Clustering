class Phrase
  def initialize(input)
    @input = input
  end

  def array_of_words
    @input.downcase.scan(/\w+/)
  end

  def word_count
    count = Hash.new(0)
    array_of_words.each_with_object(count) do |word, index|
      count[word] += 1
    end
    count
  end

end
