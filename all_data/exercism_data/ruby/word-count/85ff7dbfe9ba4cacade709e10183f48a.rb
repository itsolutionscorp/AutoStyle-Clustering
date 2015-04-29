class Phrase

  def initialize(input)
    @input = input
  end

  def word_count
    Hash.new(0).tap do |counts|
      @input.downcase.scan(/\w+/).each { |word| counts[word] += 1 }
    end
  end

end
