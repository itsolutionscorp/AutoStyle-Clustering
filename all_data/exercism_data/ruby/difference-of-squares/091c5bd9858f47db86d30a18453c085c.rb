class Squares

  attr_reader :max
  def initialize(max)
    @max = max
  end

  def square_of_sums
    answer = 0
    (1 .. max).each do |number|
      answer += number
    end
    answer**2
  end

  def sum_of_squares
    answer = 0
    (1..max).each do |number|
      answer += number**2
    end
    answer
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
