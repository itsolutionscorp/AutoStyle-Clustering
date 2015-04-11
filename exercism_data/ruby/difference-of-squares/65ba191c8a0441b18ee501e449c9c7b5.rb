class Squares
  def initialize (n)
    @num=n
  end
  # Faulhaber's Formula for O(1)
  def square_of_sums
    sum=((@num**2+@num)/2)**2
  end

  def sum_of_squares
    sum=(2*@num**3 + 3*@num**2 + @num)/6
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
