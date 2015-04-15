class Grains

  def square no
    no.times.inject(0) do |sum, i|
      if i == 0 
        sum = 1
      else   
        sum = sum * 2
      end
      sum
    end
  end
  
  def total
    64.times.inject(0) do |sum, i| 
      sum += square(i + 1) 
      sum
    end
  end
  
end
