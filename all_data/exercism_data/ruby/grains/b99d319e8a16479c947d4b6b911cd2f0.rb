class Grains

  def initialize
    @space = [0, 1, 2]
  end

  def square space_number
    @space << @space.last * 2 until @space[space_number]
    @space[space_number]
  end

  def total
    square 64
    @space.inject(:+)
  end
end
