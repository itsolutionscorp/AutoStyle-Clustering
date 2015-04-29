class Grains
  def square(n)
    squares[n] ||= 2**(n - 1)
  end

  def total
    # The sum of n successive squares is given 2**n - 1
    # 1s = a**0 + a**1 + a**2 ... + a**n
    # as = a**1 + a**2 + a**3 ... + a**(n + 1)
    # ----------------------------------------
    # (a - 1)s = a**(n + 1) - a**0
    # For a == 2, this gives s = 2**n - 1
    @total ||= 2**(64) - 1
  end

  private

  def squares
    @squares = Array.new(64)
  end

end
