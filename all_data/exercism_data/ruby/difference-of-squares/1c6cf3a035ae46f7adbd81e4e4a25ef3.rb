class Squares

  def initialize(n)
    @number = n
  end

  def sum_of_squares
    sum = 0
    (1..@number).each{ |n| sum += n**2 }
    return sum
  end

  def square_of_sums
    sum = 0
    (1..@number).each{ |n| sum += n }
    return sum**2
  end


  def difference
    square_of_sums - sum_of_squares
  end

end
