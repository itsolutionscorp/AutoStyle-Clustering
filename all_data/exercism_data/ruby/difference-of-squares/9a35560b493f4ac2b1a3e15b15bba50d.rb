class Squares

  def initialize(n)
    @n = n
  end

  def square_of_sums
    (1..@n).reduce { |acc, n| acc + n } ** 2
  end

  def sum_of_squares
    (1..@n).reduce { |acc, n| acc + n ** 2 }
                    # 0    1   0     1
                    # 1    2   1     4
                    # 5    3   5     9
                    # 14   4   14    16
                    # 30   5   30    25
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
