class Grains
  def square num
    2 ** (num - 1)
  end

  def total
    (0...64).inject(0) { |sum, num| sum += 2**num }
  end
end
