 class Squares

  def initialize(arg)
    @nums=(1..arg).to_a
  end

  def square_of_sums
    (@nums.inject(:+))**2
  end

  def sum_of_squares
    @nums.inject{|sum, num| sum + num**2}
  end

  def difference
    square_of_sums-sum_of_squares
  end

 end
