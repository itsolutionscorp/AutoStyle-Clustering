class Grains
  def square val
    2 ** (val-1)
  end

  def total
    (2 ** 64) - 1
  end
end
