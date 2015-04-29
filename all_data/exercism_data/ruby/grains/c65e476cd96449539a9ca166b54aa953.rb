class Grains
  def square(yum)
    if yum == 1
      1
    elsif yum>=2    
      2**(yum-1)
    end
  end
  
  def total
    count=sum=0
    64.times do
      count+=1
      sum+=(2**(count-1))
     # puts "count = " + count.to_s + " sum = " + sum.to_s
    end 
    return sum
  end  
end
