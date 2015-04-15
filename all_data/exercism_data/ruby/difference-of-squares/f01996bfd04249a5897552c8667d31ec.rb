class Squares
  def initialize(num)
    @range = (1..num)
  end
  def sum_of_squares
    sum = 0
    @range.each {|item| sum+= item**2}
    sum
  end

  def square_of_sums
    sum = 0
    @range.each {|item| sum+= item}
    sum = sum**2
    sum
  end

  def difference
    diff = square_of_sums - sum_of_squares
  end
end
