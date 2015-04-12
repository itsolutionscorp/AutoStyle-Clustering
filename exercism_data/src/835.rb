def compute(a,b)
#  	i = 0
  	hammingScore = 0
#  	testLength = 0
# 	strand1List = a.split("")
# 	strand2List = b.split("")
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


#  	for nucleotide in smallerList
# 			if nucleotide != biggerList[i]
# 				hammingScore += 1
# 			end
# 			i +=1
# 		end
  	return hammingScore
 	end