class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    answer = (1..@num).inject(0){|sum,number| sum + number}
    @answer = answer**2
    return @answer
  end

  def sum_of_squares
    @result = (1..@num).inject(0){|sum,number| sum + number**2}
    return @result
  end

  def difference
    square_of_sums
    sum_of_squares
    diff = @answer - @result
    return diff
  end
end
