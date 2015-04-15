class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    sum = 0
    (1..@num).each do |i|
      sum += i
    end
    return sum**2
  end

  def sum_of_squares
    square = 0
    (1..@num).each do |i|
      square = square + i**2
    end
    return square
  end

  def difference
    return (square_of_sums - sum_of_squares)
  end

  # if __file__ = $0
  #   dos = Difference_of_squares.new(5)
  #   puts dos.square_of_sums
  #   puts dos.sum_of_squares
  #   puts dos.difference
  # end

end
