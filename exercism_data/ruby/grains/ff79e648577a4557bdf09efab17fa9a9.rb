class Grains
  @@num_squares = 64

  def square(n)
    # Return number of grains on square number n
    2**(n-1)
  end

  def total
    # Return total number of grains on all squares
    # sum(2**1 .. 2**(n-1)) = (2**n) - 1
    (2**@@num_squares) - 1
  end
end
