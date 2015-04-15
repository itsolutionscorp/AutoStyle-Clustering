class Squares

  def initialize num
    @num = num
  end

  def square_of_sums
    sum = (1..@num).to_a.reduce(:+)
    sum**2
  end

  def sum_of_squares
    sum = 0
    (1..@num).to_a.each { |n| sum += n**2 }
    sum
  end

  def difference
    square = Squares.new(@num)
    (square.sum_of_squares - square.square_of_sums).abs
  end

end
