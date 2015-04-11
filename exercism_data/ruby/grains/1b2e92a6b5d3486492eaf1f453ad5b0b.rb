class Grains

  def total
    2 ** 64 - 1
  end

  def square(n)
    1 << n-1
  end
end
