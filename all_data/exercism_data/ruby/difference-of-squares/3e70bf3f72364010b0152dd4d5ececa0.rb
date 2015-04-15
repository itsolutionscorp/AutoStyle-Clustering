class Squares
  def initialize n
    @n = n
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def sum_of_squares
    sum = 0
    (1..@n).each do |a|
      sum = sum + (a ** 2)
    end
    sum
  end

  def square_of_sums
    sum = 0
    (1..@n).each do |a|
      sum = sum + a
    end
    sum**2
  end
end
