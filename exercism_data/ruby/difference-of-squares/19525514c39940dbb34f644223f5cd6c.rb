class Squares

  def initialize(num)
    @num = num
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end

  def sum_of_squares
    result = 0
    nums = (1..@num).to_a
    nums.each {|x| result += x**2}
    result
  end

  def square_of_sums
    sum = 0
    nums = (1..@num).to_a
    nums.each {|x| sum += x}
    result = sum**2
  end
end
