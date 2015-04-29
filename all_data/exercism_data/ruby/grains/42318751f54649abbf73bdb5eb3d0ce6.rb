class Grains
  def square(n)
    result = 1
    (n-1).times do
      result *= 2
    end
    result
  end

  def total
    (1..64).inject(0){|sum, num| sum + square(num)}
  end
end
