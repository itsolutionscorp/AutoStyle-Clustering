class Grains
  def square(num)
    2**(num-1) if num
  end

  def total
    sum=0
    (1..64).each{|x| sum+=square(x)}
    return sum
  end
end
