class Squares
  attr_reader :limit

  def initialize(n)
    @limit = n
  end

  def sum_of_squares
    (1..limit).inject { |total,number| total + number**2 }
  end

  def square_of_sums
    (1..limit).inject(:+)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
