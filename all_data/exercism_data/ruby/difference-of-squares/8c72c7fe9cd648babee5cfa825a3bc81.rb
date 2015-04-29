class Squares
  attr_accessor :number
  def initialize(int)
    @number = int
  end

  def square_of_sums
    (number + 1).times.inject(:+) ** 2
  end

  def sum_of_squares
    (number + 1).times.with_object([]) do |number, array|
      array << number ** 2
    end.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
