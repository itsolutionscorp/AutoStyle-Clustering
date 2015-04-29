class Hamming
  def self.compute(x, y)
    new(x, y).distance
  end

  def initialize(x, y)
    @x, @y = x, y
  end

  def distance
    distance = 0
    (0..length).each do |i|
      distance += 1 if @x[i] != @y[i]
    end
    distance
  end

  private

  def length
    [@x.length, @y.length].min - 1
  end
end
