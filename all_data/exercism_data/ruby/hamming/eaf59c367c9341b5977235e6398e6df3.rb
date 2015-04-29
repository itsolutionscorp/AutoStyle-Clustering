class Hamming
  def self.compute(first,second)
    if first.eql?(second)
     0
    else
      self.disparity(first,second)  
    end  
  
  end
  
  def self.disparity(first,second)
	disparityLevel=0
	shorter=self.shorter(first,second) 
	
	(0..shorter).each do |i|
	  if not first[i].eql?(second[i])
       disparityLevel+=1
	  end
	end	
		  
	disparityLevel		
  end
  
  def self.shorter(first,second)
   if first.length<second.length
    first.length-1
   else
	second.length-1
	end
  end

end
