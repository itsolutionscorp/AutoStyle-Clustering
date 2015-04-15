class Grains
  def square(n)
    1 << (n-1)
  end

  def total
    (1 << 64) - 1
  end
end
