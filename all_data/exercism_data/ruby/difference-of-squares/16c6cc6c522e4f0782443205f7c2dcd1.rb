class Squares

  def initialize(n)
    @n = n
  end

  def sum_of_squares
    total = 0
    for i in 1..@n do 
      total += i**2
    end
    total
  end


  def square_of_sums
    sum = 0
    for i in 1..@n do
      sum += i
    end
    total = sum **2
  end


  def difference
    square_of_sums - sum_of_squares
  end



end
