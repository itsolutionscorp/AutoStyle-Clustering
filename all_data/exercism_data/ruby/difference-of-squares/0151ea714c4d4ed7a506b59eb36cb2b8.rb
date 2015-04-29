class Squares
  def initialize(n)
    @n = n
  end

  def square_of_sums
    (n4 + 2 * n3 + n2) / 4
  end

  def sum_of_squares
    (2 * n3 + 3 * n2 + n) / 6
  end

  def difference
    (n4 - n2) / 4 + (n3 - n) / 6
  end

  private

  attr_reader :n

  def n2
    n**2
  end

  def n3
    n**3
  end

  def n4
    n**4
  end
end
