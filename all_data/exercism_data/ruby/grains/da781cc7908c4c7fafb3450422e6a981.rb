class Grains

  ENTIRE_BOARD = 1..64

  def square(num)
    2 ** (num - 1) 
  end

  def total
    (ENTIRE_BOARD).inject{ |sum, i| sum + square(i) }
  end

end
