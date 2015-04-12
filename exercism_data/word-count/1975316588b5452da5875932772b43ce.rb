class Phrase
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def word_count
    count_words
  end

  def count_words
    input_split_into_downcased_words.each_with_object(Hash.new(0)) { |word, counts| counts[word] += 1 }
  end

  def input_split_into_downcased_words
    input.downcase.scan(/\w+/)
  end
end
