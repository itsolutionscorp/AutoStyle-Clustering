class Squares
  def initialize (num)
    @num = (1..num)
  end

  def square_of_sums
    @num.inject do |sum, num|
      sum + num
    end ** 2
  end

  def sum_of_squares
    @num.inject do |sum, num|
      sum + num ** 2
    end
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
end
