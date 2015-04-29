class Phrase
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, counts| counts[word] += 1 }
  end

  def words
    input.downcase.scan(/\w+/)
  end
end
