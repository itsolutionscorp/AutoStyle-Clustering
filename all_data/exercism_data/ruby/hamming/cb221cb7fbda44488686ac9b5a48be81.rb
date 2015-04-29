class Hamming

  def self.compute(first, second)
    HammingComputer.new(first, second).compute
  end

end

class HammingComputer

  def initialize(first, second)
    @first = first
    @second = second
  end

  def compute
    (0...length_of_shortest_strand).reduce(0) {|sum, index| sum + distance_value_at_index(index)}
  end

  private

  attr_reader :first, :second, :length

  def length_of_shortest_strand
    [first.length, second.length].min
  end

  def distance_value_at_index(index)
    first[index] != second[index] ? 1 : 0
  end

end
