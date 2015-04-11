class Squares
  attr_reader :number

  def initialize(n)
    @number = n
  end

  def square_of_sums
    [*1..number].inject(&:+) ** 2
  end

  def sum_of_squares
    [*1..number].inject do |sum, n|
      sum + (n ** 2)
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
