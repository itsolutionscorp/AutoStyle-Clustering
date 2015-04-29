class Squares
  def initialize(num)
    @num = num
  end


  def square_of_sums
    # Returns the sqaure of sums from 1 to num
    sum = 0

    (1..@num).each do |i|
      sum += i
    end

    sum ** 2
  end


  def sum_of_squares
    # Returns the sum of squares from 1 to num
    sum = 0

    (1..@num).each do |i|
      sum += i ** 2
    end

    sum
  end


  def difference
    # Returns the difference between the two functions square_of_sums to sum_of_squares
    square_of_sums - sum_of_squares
  end
end
