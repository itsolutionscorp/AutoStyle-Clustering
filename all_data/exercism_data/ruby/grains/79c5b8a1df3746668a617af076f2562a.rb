class Grains
  def square(sq)
    raise "Square number should be positive!" if sq <= 0
    2**(sq-1)
  end
  
  def total
    (0..63).inject(0){ |sum, x| sum += 2**x }
  end
end
