class Squares
  def initialize(index) # index = max number in range
    @range = Range.new(1, index)
  end

  def square_of_sums
    sum = @range.reduce do |sum, index|
      sum + index
    end
    sum * sum
  end

  def sum_of_squares
    @range
    .map { |index| index * index }
    .reduce do |sum, index|
      sum + index
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
