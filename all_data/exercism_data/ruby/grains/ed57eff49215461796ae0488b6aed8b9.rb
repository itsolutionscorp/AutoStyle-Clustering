class Grains
  def initialize
    @grains = (0..64).map { |n| 2**(n - 1) }
  end

  def square(n)
    @grains[n]
  end

  def total
    @grains.inject(:+).to_i
  end
end
