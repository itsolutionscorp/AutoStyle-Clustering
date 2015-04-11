class Squares
  def initialize(count)
    @count = count
  end

  def difference
     square_of_sums - sum_of_squares
  end

  def sum_of_squares
    (1..@count).inject(0) do |sum, i|
      sum += i**2 
    end
  end

  def square_of_sums
    (1..@count).inject(:+) ** 2
  end
end
