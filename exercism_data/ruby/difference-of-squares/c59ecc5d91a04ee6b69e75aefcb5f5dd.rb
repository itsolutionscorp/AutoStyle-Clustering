class Squares

  def initialize(n)
    @n = n
  end

  def sum_of_squares
    total = 0
    (1..@n).each {|i| total += i**2}
    total
  end


  def square_of_sums
    sum = 0
    (1..@n).each {|i| sum += i}
    total = sum **2
  end


  def difference
    square_of_sums - sum_of_squares
  end



end
