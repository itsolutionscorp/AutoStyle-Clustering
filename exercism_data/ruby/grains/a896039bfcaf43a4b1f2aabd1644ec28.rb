class Grains
  # square returns the number of grains on the nth square of a chess board.
  def square(n)
    if n < 1 || n > 64
      raise ArgumentError.new("n must be between 1 and 64.")
    end

    1 << (n-1)
  end

  # total returns the toal number of grains on the chess board.
  def total
    # Each square has 1 << (n-1) grains of rice. In the binary representation
    # square one therefore has bit 1 set, square two bit 2 and so on. If we sum
    # all squares up, we end up with a number in whose binary representation
    # bits 1 through 64 are set.
    0xFFFFFFFFFFFFFFFF
  end
end
