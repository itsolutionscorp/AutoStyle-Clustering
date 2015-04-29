class Squares
  def initialize(n)
    @n = n
  end

  def sum_of_squares
    # hooray for math articles on wikipedia
    @n * (@n + 1) * (2*@n + 1) / 6
  end

  def square_of_sums
    ((@n + 1) * @n / 2 ) **2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
