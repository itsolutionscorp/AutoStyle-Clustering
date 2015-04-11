class Grains
  attr_reader :total
  def initialize
    @total = 18446744073709551615
  end

  def square(size)
    (1..size).reduce { |accumulator, _| accumulator * 2 }
  end
end
