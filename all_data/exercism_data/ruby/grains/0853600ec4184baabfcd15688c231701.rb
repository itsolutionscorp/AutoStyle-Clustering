class Grains
  def square(number)
    2**(number-1)
  end
  def total(size=64)
    (1..size).inject { |sum, n| sum + square(n) }
  end
end
