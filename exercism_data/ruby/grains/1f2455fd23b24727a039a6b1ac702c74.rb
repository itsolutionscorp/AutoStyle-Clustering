class Grains

  def square(n)
    2**(n-1)
  end

  def total
    sum = 0
    (0...64).each do |n|
      sum += 2**n
    end
    sum
  end
end
