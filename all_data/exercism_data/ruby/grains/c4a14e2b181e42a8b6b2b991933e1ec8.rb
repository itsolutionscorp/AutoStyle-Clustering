# encoding: UTF-8
# Clase Grains
class Grains
  def square(n)
    2**(n - 1)
  end

  def total
    @total = 0
    (1..64).each { |i| @total += square(i) }
    @total
  end
end
