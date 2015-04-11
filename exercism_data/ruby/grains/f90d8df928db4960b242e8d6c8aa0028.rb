class Grains
  def square(n)
    chessboard_sum(n).last
  end

  def total
    chessboard_sum.inject(&:+)
  end

  def chessboard_sum(n = 64)
    (1...n).each_with_object([1]) { |i, arr| arr[i] = arr[i-1] * 2 }
  end
end
