class Squares
  def initialize(v)
    @sq = v
  end

  def square_of_sums
    sum = 0
    (0..@sq).each{|x|sum += x}
    sum * sum
  end

  def sum_of_squares
    sum = 0
    (0..@sq).each{|x|sum += x*x}
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
