class Grains
  # square returns the number of grains on the nth square of a chess board.
  def square(n)
    1 << (n-1)
  end

  # total returns the toal number of grains on the chess board.
  def total
    # Each square has 1 << (n-1) grains of rice, one more than all squares
    # before it combined. The total number of grains on a chess board can
    # thus be calculated by subtracting one from the number of grains on a
    # theoretical 65th square.
    square(65)-1
  end
end
