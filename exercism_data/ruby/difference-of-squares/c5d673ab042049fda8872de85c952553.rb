class Squares
  def initialize(num)
    if num.is_a?(Integer) && num > 0
      @num = num
    else
      raise ArgumentError.new("positive integer required")
    end
  end

  def difference
    (square_of_sums - sum_of_squares).abs
  end

  def sum_of_squares
    (0..@num).inject { |hold, num| hold + num**2 }
  end

  def square_of_sums
    ((0..@num).inject(:+))**2
  end
end
