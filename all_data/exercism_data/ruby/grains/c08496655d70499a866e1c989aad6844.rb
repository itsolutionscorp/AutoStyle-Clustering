class Grains
  def square num
    2 **(num - 1)
  end

  def total 
    (0...64).inject(0) { |sum, n| sum += 2**n }
  end
end
