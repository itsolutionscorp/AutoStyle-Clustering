class Squares
  def initialize(limit)
    @limit = limit
  end

  def square_of_sums
   (@limit+1).times.reduce(:+)**2
#  (1..@limit).reduce(:+)**2
  end

  def sum_of_squares
    @limit.times.each.map { |i| (i+1)**2 }.reduce(:+)
#   @limit.times.with_index(1).each.map { |i,j| (j)**2 }.reduce(:+)
#   (1..@limit).map { |i| i**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
