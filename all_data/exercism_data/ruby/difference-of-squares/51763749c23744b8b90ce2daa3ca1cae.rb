class Squares
  def initialize(nb)
    @number = nb
  end

  def square_of_sums
    1.upto(@number).inject(:+) ** 2
  end

  def sum_of_squares
    1.upto(@number).inject { |acc, i| acc + i**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
