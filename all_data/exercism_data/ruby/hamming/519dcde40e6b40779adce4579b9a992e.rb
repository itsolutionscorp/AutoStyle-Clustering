class Hamming

  attr_accessor :char_array_one, :char_array_two

  def self.compute(string_one, string_two)
    Hamming.new(string_one, string_two).count
  end

  def initialize(string_one, string_two)
    @char_array_one = string_one.split("")
    @char_array_two = string_two.split("")
  end

  def count
    hamming_difference(char_array_one, char_array_two)
  end


  def trimmed_array(first,second)
    desired_length = second.count
    return first[0..(desired_length-1)] if first.length > second.length
    first
  end

  def hamming_difference(first, second)
     trimmed_array(first,second).zip(trimmed_array(second,first)).inject(0) do  |sum, char_pair|
      char_pair.first == char_pair.last ? sum : sum + 1
    end

  end

end
