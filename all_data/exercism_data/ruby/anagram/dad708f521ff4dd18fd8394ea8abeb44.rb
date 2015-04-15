class Anagram
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def match(data = [])
    data.select do |word|
      word == input
    end
  end

end
