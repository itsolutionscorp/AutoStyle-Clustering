class Grains

  def initialize
    @squares = []
    (0...64).each do |el|
      @squares << (1 << el)
    end
  end

  def square(num)
    @squares[num-1]
  end

  def total
    @squares.reduce(:+)
    seed = 0
    64.times do
      seed = (seed << 1) | 1
    end
    seed
  end
end
