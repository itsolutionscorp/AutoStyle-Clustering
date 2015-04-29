class Grains
  NUMBER = 64

  def square(n)
    2 ** (n - 1)
  end

  def total
    square(NUMBER + 1) - 1
  end
end
