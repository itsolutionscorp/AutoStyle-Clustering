class Squares
  def initialize prompt
    @num = prompt
  end

  def square_of_sums
    sum = 0; (1..@num).each{|x| sum += x}
    sum**2
  end

  def sum_of_squares
    sum = 0; (1..@num).each{|x| sum += x**2}
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
