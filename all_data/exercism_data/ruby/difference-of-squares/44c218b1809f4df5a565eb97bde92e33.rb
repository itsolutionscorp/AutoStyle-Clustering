class Squares
  def initialize(num)
    @range = (1..num).to_a
  end

  def square_of_sums
    @range.inject(0) {|acc, num| 
      acc+num
    }**2
  end

  def sum_of_squares
    @range.inject(0) {|acc, num|
      acc + num**2
    }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
