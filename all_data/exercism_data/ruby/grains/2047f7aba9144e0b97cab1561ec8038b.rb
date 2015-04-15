# -*- coding: UTF-8 -*-
# Write a program that calculates the number of grains of wheat on a
# chessboard given that the number on each square doubles.
class Grains
  def square(num)
    2**(num - 1)
  end

  def total
    total = 0
    i = 1
    until i > 64
      total += square(i)
      i += 1
    end
    total
  end
end
