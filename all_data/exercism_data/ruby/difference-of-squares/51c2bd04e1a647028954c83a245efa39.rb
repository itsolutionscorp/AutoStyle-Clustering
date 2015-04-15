require 'byebug'

class Squares
  def initialize(n)
    @number = n
  end
  def square_of_sums
    i = 0
    j = 0
    while i <= @number
      j += i
      i = i + 1
    end
    (j**2)
  end
  def sum_of_squares
    i = 0
    j = 0
    while i <= @number
      j += i**2
      i = i + 1
    end
    j
  end
  def difference
    square_of_sums - sum_of_squares
  end
end
