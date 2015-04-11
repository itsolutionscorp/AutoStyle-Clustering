class Grains
  def square(num, grains = 1)
    return grains if num <= 1
    square(num - 1, grains *= 2 )
  end

  def total
    (1..64).inject { |sum, num| sum + square(num) }
  end
end
