class Grains
  CHESSBOARD_SIZE = 64

  def square(num)
    2 ** (num - 1)
  end

  def total
    CHESSBOARD_SIZE.times.inject(0) { |sum, i| sum += square(i + 1) }
  end
end
