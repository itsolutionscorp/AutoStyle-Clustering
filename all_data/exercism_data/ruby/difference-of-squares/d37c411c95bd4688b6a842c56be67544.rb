class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    sum = 0
    1.upto(@num) do |n|
      sum += n
    end
    return sum**2
  end

  def sum_of_squares
    arr = []
    1.upto(@num) do |n|
      arr << n**2
    end
    return arr.inject(0) { |sum, n| sum += n }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
