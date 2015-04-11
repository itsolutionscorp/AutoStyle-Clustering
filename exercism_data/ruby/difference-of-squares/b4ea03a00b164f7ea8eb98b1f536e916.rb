#!/usr/bin/ruby

class Squares

  def initialize(num_in)
    @my_num = num_in
  end

  def difference()
    result = square_of_sums() - sum_of_squares()
  end

  def sum_of_squares()
    i=1
    result = 0

    while i <= @my_num do
      result = result + (i**2)
      i += 1
    end

    result
  end

  def square_of_sums
    result = ((1 + @my_num) * (@my_num /2.0))**2
  end

end
