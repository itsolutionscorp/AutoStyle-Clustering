class Squares
  def initialize(count)
    @count = count
  end

  def square_of_sums
    sum = 0
    (1..@count).each do |i|
      sum += i
    end
    value = sum**2
  end

  def sum_of_squares
    sum = 0
    (1..@count).each do |i|
      sum += i**2
    end
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
