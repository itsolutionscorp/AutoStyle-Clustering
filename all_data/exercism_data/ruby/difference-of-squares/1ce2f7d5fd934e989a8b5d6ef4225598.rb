class Squares

  POWER_OF = 2

  def initialize(num)
    @natural_num_range = (1..num)
  end

  def square_of_sums
    @natural_num_range.inject(:+) ** POWER_OF
  end

  def sum_of_squares
    @natural_num_range.inject do |sum, n|
      n ** POWER_OF + sum
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
