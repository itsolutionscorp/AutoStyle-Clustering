class Hamming
  def self.compute(x, y)
    new(x, y).distance
  end

  def initialize(x, y)
    @x, @y = x, y
  end

  def distance
    length = [@x, @y].map(&:length).min - 1
    (0..length).count { |i| @x[i] != @y[i] }
  end
end
