class Squares
  def initialize(int)
    @int = int
  end

  def square_of_sums
    sum = 0
    @int.times do |counter|
      sum = sum + (counter + 1)
    end
    sum**2
  end

  def sum_of_squares
    sum = 0
    @int.times do |counter|
      sum = (sum + (counter + 1)**2)
    end
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
