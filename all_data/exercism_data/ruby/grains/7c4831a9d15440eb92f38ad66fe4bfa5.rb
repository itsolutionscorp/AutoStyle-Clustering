class Grains
  def square(n)
    0b10 << n - 0b10
  end

  def total
    (0b10 << 0b111111) - 0b1
  end
end
