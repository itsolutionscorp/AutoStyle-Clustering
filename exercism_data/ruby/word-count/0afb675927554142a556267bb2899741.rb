class Phrase
  attr_reader :input 

  def initialize(input)
    @input = input.downcase.scan(/\w+/)

  end

  def word_count
      counts = Hash.new(0)
      @input.each { |word| counts[word] += 1}
      return counts
  end

end
