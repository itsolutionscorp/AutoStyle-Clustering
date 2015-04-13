def compute(a,b)

  	hammingScore = 0



  	biggerList = []
  	smallerList = []


  	if a.length > b.length
  		biggerList = a.split("")
  		smallerList = b.split("")
  	else
  		biggerList = b.split("")
  		smallerList = a.split("")
  	end

  	smallerList.each_with_index do |nucleotide, i|
  		if nucleotide != biggerList[i]
  			hammingScore += 1
  		end
  	end








  	return hammingScore
 	end