class Squares
  attr_reader :count

  def initialize(count)
    @count = count
  end

  def square_of_sums
    (1..count).inject(:+) ** 2
  end

  def sum_of_squares
    (1..count).inject(0) do |result, element|
      result += element ** 2
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
