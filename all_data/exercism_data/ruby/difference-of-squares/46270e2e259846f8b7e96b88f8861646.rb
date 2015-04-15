class Squares
  def initialize(upper_bound)
    @numbers = (1..upper_bound).to_a
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square_of_sums
    @numbers.inject(:+)**2
  end

  def sum_of_squares
    @numbers.map{|n| n**2}.inject(:+)
  end
end
