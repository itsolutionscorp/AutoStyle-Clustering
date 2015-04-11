class Grains
 def square(i)
  2**(i-1)
 end
 
 def total
 sum=0
 64.times{|i|sum+=square(i+1)}
 sum
 end
end
