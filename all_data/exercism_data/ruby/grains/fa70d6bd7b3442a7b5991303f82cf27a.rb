class Grains
  def initialize(size=64)
    @size = size
  end
  def square(id)
    1 << (id - 1)
  end
  def total
    square(@size+1) - 1
  end
end
