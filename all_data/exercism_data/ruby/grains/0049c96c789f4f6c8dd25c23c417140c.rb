# 1, 2, 4, 8, 16, ...
class Grains
  def square x
    (2 ** (x-1)) 
  end

  def total
    sum = 0
    for i in (1..64)
      sum += square(i)
    end
    sum
  end
end
