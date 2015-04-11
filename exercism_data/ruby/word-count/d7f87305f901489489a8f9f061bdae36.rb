class Phrase

  attr_reader :input
  def initialize(input)
    inputs = input.downcase.delete(":!&@%^&$").split(/\W+/)
      @data = Hash.new(0)
      inputs.each do |word|
        if @data[word]
          @data[word] += 1

    end
  end

  def word_count
    @data
  end

end
