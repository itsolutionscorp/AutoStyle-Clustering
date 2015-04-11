class Grains

  def square number
    1 << (number - 1)
  end  

  def total
    (1 << 64) - 1
  end
end
