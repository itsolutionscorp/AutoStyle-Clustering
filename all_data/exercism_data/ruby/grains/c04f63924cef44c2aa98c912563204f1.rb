class Grains

  attr_reader :grains

  def initialize
    @grains = (0..63).map {|i| 2**i }
  end

  def square(cell)
    @grains[cell-1]
  end

  def total
    @grains.inject(:+)
  end

end
