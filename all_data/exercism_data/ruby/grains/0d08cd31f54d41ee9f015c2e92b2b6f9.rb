class Grains
  GRAINS_MAX = 64

  def square(num)
    1 << (num - 1)
  end

  def total
    1.upto(GRAINS_MAX).inject { |total, num| total + square(num) }
  end
end
