require 'pry'

class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    sum_num = @num
    sum = 0
    while sum_num > 0
      sum += sum_num
      sum_num -= 1
    end
    sum ** 2
  end

  def sum_of_squares
    squared_num = @num
    total = 0
    while squared_num > 0
      total += squared_num ** 2
      squared_num -= 1
    end
    total
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
