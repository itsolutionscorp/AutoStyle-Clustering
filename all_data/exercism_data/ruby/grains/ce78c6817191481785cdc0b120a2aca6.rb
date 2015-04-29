class Grains
  def square(sq)
    raise "Square number should be positive!" if sq <= 0
    2**(sq-1)
  end
  
  def total
    2**64 - 1
  end
end
