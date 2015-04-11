class Squares

  def initialize(integer)
    @integer = integer
  end

  def square_of_sums
    sum = (1..@integer).inject(:+)
    sum ** 2
  end

  def sum_of_squares
    sum = 0
    (1..@integer).each do |number|
      sum += number ** 2
    end
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
