class Grains

  def square(n)
    return 2**(n-1)
  end


  def total
    sum = 0
    1.upto(64) do |x|
       sum = square(x) +  sum
    end
    return sum
  end
end
