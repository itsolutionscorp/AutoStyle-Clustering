class Hamming
  def self.compute(first, second)
    new(first, second).find_differences
  end

  attr_reader :sequence_one, :sequence_two

  def initialize(sequence_one, sequence_two)
    @sequence_one = sequence_one.chars
    @sequence_two = sequence_two.chars
  end

  def find_differences
    min_length = [sequence_one.length, sequence_two.length].min
    (0...min_length).count { |index| sequence_one[index] != sequence_two[index] }
  end
end
