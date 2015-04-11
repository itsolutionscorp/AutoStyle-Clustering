class Hamming
  def self.compute(x, y)
    new(x, y).distance
  end

  def initialize(x, y)
    @x, @y = x, y
  end

  def distance
    (0..length).reduce(0) do |sum, pos|
      sum += 1 if @x[pos] != @y[pos]
      sum
    end
  end

  private

  def length
    [@x.length, @y.length].min - 1
  end
end
