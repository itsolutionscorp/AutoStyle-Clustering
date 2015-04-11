class Squares
  def initialize(integer)
    @integer = integer
  end

  def square_of_sums
    sum = (1..@integer).inject { |accumulator, integer| accumulator + integer }
    sum ** 2
  end

  def sum_of_squares
    (1..@integer).inject { |accumulator, integer| accumulator + integer ** 2 }
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end
end
