class Grains

  def square(n)
    2 ** (n-1)
  end

  def total
    (1..64).inject {|enum, num| enum += square(num)}
  end
end
