class Grains

  TOTAL = 64

  def square(num)
    2 ** (num - 1)
  end

  def total
    2 ** TOTAL - 1
  end

  def total_base(base)
    (1 - base ** TOTAL) / (1 - base)
  end

end
