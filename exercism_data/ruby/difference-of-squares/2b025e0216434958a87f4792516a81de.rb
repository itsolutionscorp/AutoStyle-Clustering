class Squares

  def initialize(value)
    @num = value
  end

  def square_of_sums
    result = 0

    @num.times do |i|
      result += i + 1
    end

    result**2
  end

  def sum_of_squares
    result = 0

    @num.times do |i|
      result += (i+1)**2
    end

    result
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
