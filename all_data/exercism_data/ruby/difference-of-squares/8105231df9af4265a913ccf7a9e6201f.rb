class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def square_of_sums
    sum**2
  end

  def sum_of_squares
    sum { |i| i**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def sum
    range.inject(0) do |memo, i|
      memo += block_given? ? yield(i) : i
    end
  end

  def range
    1..number
  end
end
