class Squares
  def initialize(input)
    @input = input
  end
  def square_of_sums
    output = 0
    (1..@input).each do |i|
      output += i
    end
    output**2
  end

  def sum_of_squares
    output = 0
    (1..@input).each do |i|
      output += i**2
    end
    output
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
