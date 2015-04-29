class Squares
  def initialize(n)
    @n = n
  end

  def sum_of_squares
    sum = 0
    @n.times do |k|
      sum += (k + 1) ** 2
    end
    sum
  end

  def square_of_sums
    sum = 0
    @n.times do |k|
      sum += (k + 1)
    end
    sum ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
