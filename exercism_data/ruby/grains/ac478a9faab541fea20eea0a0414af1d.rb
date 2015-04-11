# encoding: UTF-8
# Clase Grains
class Grains
  def total
    sum = 0
    i = 1
    while i <= 64
      sum += square(i)
      i += 1
    end
    sum
  end

  def square(num)
    (2**num) / 2
  end
end
