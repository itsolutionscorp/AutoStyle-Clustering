class Phrase
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def word_count
    frequency = Hash.new(0)
    words = input.downcase.gsub("'", '_').gsub(/\W+/, ' ').gsub('_', "'")
    words.split.each do |word|
      frequency[word] += 1
    end
    frequency
  end
end
