class Squares

  def initialize(input)
    @input = input
  end

  def square_of_sums
    sum = 0
    (0..@input).each do |num|
      sum+=num
    end
    return sum**2
  end

  def sum_of_squares
    sum = 0
    (0..@input).each do |num|
      sum += num**2
    end
    return sum
  end

  def difference
    square_of_sums() - sum_of_squares()
  end

end
