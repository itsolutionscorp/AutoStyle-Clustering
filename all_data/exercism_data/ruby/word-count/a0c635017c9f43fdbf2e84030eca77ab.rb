class Phrase
  def initialize(input)
    @input = input
  end

  def array_of_words
    @input.downcase.scan(/\w+/)
  end

  def word_count
    array_of_words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end
end
