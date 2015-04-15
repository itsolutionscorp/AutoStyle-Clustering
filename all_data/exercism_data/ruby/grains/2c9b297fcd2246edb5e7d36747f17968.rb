class Grains
  def square(num)
    num == 1 ? 1 : square(arg-1) * 2
  end

  def total
    (1..64).inject(0){|sum, n| sum + square(n)}
  end
end
