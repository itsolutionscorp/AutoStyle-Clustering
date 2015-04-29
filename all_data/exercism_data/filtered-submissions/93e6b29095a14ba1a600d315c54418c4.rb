class Hamming
  
  def Hamming.compute(dnaone, dnatwo)  
    difference = 0    
    if dnaone.length < dnatwo.length then
    	endofchain = dnaone.length-1
    else
    	endofchain = dnatwo.length-1
    end
    
    for i in 0..endofchain
    	if dnaone[i] != dnatwo[i] then
    		difference+=1
    	end
    end
    return difference
  end  
    
end
