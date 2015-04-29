class Grains
  
  def square(x)
    2**(x-1)
  end
  
  def total
    (1..64).inject(0) {|sum, x| sum += square(x)}
  end
  
end
