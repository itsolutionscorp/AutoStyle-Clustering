class Hamming
  
  def Hamming.compute(dnaone, dnatwo)  
  	difference = 0    
    if dnaone != dnatwo then
    	dnaone.length < dnatwo.length ? (endofchain = dnaone.length-1) : (endofchain = dnatwo.length-1)

    	for i in 0..endofchain
    		if dnaone[i] != dnatwo[i] then
    			difference+=1
    		end
    	end
    end
    return difference
  end  
    
end
