class Grains

  def initialize
    @grains = Array.new(64)
    @grains[0] = 1

    (1...64).each { |i| @grains[i] = @grains[i-1]*2 }
  end

  def square(n)
    @grains[n-1]
  end

  def total
    @grains.inject(:+)
  end

end
