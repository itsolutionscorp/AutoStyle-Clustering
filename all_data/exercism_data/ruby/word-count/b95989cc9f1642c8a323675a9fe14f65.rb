class Phrase
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def words
    input.downcase.scan(/[\w']+/)
  end

  def word_count
    count = Hash.new(0)
    words.each do |word|
      count[word] += 1
    end
    return count
  end

end
