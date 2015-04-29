class Squares
  def initialize(x)
    @x = x
  end

  def square_of_sums
    (@x+1).times.reduce(:+)**2
  end

  def sum_of_squares
    result = 0
    @x.times do |i|
      result += (i+1)**2
    end
    result
#   @x.times { |i| (i+1)**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
