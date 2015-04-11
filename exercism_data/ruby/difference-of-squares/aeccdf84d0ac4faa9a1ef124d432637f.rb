class Squares
  attr_reader :n

  def initialize(n)
    @n = n
  end

  def square_of_sums
    ((n + 1) * n / 2)**2
  end

  def sum_of_squares
    n * (n + 1) * (2*n + 1) / 6
  end

  def difference
    # Fun:
    #   If you like you can factor out a term of sum to make this marginally
    #   faster to compute:
    #   n(n + 1) / 2 <- sum
    #   n(n + 1)(2n + 1) / 6 <- sum_of_squares 
    #   n(n + 1)/2 * ((2n+1)/3)
    #   sum * (2n + 1)/3
    #   ------------------------------------------------
    #   sum * sum - sum * (2n + 1)/3 = sum - (2n + 1)/3 <- difference
    square_of_sums - sum_of_squares
  end
end
