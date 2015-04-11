class Grains
  def square(size)
    2**(size-1)
  end

  def total
    (1..64).map { |n| square(n) }.inject(&:+) 
  end
end
