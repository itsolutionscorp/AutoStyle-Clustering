class Grains
  def total()
  	sum = 0
  	i = 1
    while i <= 64
    	sum += square(i)
    	i+=1
    end
    return sum
  end

  def square(num)
    return (2**num)/2
  end  
end
