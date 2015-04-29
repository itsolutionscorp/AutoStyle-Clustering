class Squares
  def initialize(n)
    @n = n
  end
  
  def square_of_sums
    total = 0
    (1..@n).each {|x| total += x}
    return total**2
  end
  
  def sum_of_squares
    total = 0
    (1..@n).each {|x| total += x**2}
    return total
  end
  
  def difference
    return square_of_sums - sum_of_squares
  end
end
