class Grains

  def square(n)
   return  2**(n-1)
  end

  def total
    (1..64).inject do |sum,x|
      square(x) + sum
    end
  end
end
