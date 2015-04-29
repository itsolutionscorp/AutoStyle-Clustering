class Grains
  BASE = 2
  SQUARES = 64
  
  def square position
    BASE**(position-1)
  end
  
  def total
    (1..SQUARES).reduce{|sum, pos| sum + square(pos) }
  end
end
