class Squares

  attr_reader :length

  def initialize length
    @length = length.to_i
  end

  def square_of_sums
    sum = 0
    (1..length).each do |num|
      sum += num 
    end
    sum**2
  end

  def sum_of_squares
    sum = 0
    (1..length).each do |num|
      sum += num**2
    end
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
