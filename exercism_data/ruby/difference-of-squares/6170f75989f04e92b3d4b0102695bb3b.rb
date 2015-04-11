class Squares
  def initialize(int)
    @int = int
  end

  def square_of_sums
    (1..@int).inject(:+)**2
  end

  def sum_of_squares
    (1..@int).map { |i| i**2 }.inject(:+)
  end
  
  def difference
    self.square_of_sums - self.sum_of_squares
  end

end
