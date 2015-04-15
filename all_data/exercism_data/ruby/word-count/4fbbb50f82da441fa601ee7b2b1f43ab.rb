class Phrase
  def initialize(input)
    @input = input
  end

  def array_of_words
    @input.downcase.scan(/\w+/)
  end

  def word_count
    occurence_list = Hash.new(0)
    array_of_words.each_with_index do |value, index|
      occurence_list[value] += 1
    end
    occurence_list
  end

end
