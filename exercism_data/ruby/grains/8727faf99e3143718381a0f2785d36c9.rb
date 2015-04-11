class Grains

  def square(n)
    2 ** (n - 1)
  end

  def total
    2 ** @@chessboard_size - 1
  end

private
  @@chessboard_size = 64

end
