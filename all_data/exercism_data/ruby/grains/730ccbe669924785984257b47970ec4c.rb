class Grains
  def square(num)
    return 2 ** (num - 1)
  end

  def total
    (1..64).inject(0){ |sum, num| sum += 2 ** (num - 1) }
  end
end
