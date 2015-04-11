class Squares
  def initialize(n)
    @number = n
  end

  def square_of_sums
    @square ||= factorial.inject(:+) ** 2
  end

  def sum_of_squares
    @sum ||= factorial.inject(0) { |acc, val| acc + val ** 2 }
  end

  def difference
    @difference ||= square_of_sums - sum_of_squares
  end

  private

  def factorial
    @factorial ||= @number.downto(1)
  end
end
