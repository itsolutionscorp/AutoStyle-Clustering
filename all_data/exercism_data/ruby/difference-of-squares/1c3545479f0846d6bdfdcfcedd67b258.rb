class Squares
  def initialize(value)
    @value = value
  end

  def sum_of_squares
    result = 0
    (1..@value).each do |number|
      calculation = number**2
      result += number**2
    end
    result
  end

  def square_of_sums
    added = (1..@value).reduce(:+)
    added**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
