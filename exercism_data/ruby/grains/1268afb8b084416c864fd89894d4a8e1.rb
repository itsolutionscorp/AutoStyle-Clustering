class Grains
  attr_reader :total
  def initialize
    @total = 18446744073709551615
  end

  def square(size)
    2 ** (size - 1)
  end
end
