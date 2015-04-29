class Phrase
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def word_count
    count_words(convert_input)
  end

  def convert_input(statement=input)
    statement.downcase.gsub(/\W+/, ' ')
  end

  def count_words(prepared_input)
    counts = Hash.new(0)
    prepared_input.scan(/\w+/) { |word| counts[word] += 1 }
    counts
  end
end
