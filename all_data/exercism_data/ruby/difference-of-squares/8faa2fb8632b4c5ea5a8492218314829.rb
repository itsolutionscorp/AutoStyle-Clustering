class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    sum = 0
    [*1..@number].each{|x| sum += x}
    sum**2
  end

  def sum_of_squares
    [*1..@number].inject(0){|sum,x| sum += x**2}
  end

  def difference
     square_of_sums - sum_of_squares
  end
end
