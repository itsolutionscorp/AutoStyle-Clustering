class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    (1..@num).inject(:+) ** 2
  end

  def sum_of_squares
    x = 0
    (1..@num).each do |num|
      x += num ** 2
    end
    return x
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
