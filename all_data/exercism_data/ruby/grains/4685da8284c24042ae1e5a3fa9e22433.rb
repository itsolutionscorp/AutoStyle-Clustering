class Grains
  MAX_SQUARES = 64

  def square(n)
    2**(n - 1)
  end

  def total
    square(MAX_SQUARES + 1) - 1
  end
end
