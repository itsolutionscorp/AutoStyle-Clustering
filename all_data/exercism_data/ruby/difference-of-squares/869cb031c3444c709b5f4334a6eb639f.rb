class Squares
  def initialize(upper_bound)
    @upper_bound = upper_bound
  end

  def square_of_sums
    @square_of_sums ||= sum(range) ** 2
  end

  def sum_of_squares
    @sum_of_squares ||= sum(range.map {|value| value ** 2 })
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  attr_reader :upper_bound

  def range
    1..upper_bound
  end

  def sum(values)
    values.reduce(:+)
  end
end
