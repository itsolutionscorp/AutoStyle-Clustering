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

  	[smallerList.zip(biggerList)].each do |nucleotideA, nucleotideB|
  		if nucleotideA != nucleotideB
  			hammingScore += 1
  	end








  	return hammingScore
 	end