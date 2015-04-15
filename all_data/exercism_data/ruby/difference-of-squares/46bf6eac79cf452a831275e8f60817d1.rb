class Squares
  def initialize(arg)
    @args = (1..arg)
  end

  def square_of_sums
    @args.reduce(:+)**2
  end

  def sum_of_squares
    @args.map { |e| e**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
