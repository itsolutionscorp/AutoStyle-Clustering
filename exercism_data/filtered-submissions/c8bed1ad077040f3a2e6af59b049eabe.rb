def compute(dnaone, dnatwo)  
  	difference = 0 
    endofchain = [dnaone.length,dnatwo.length].min
  	for i in 0...endofchain
   		difference+=1 if dnaone[i] != dnatwo[i]
   	end
    return difference
  end