class Phrase

  attr_reader :input

  def initialize(words)
    @input = words.downcase.scan(/[\w']+/)
  end

  def word_count
    frequency = Hash.new(0)
    for word in @input
      frequency[word] += 1
    end
    frequency
  end

end
