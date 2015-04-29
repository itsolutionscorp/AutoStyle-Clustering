class Hamming

  def self.compute(a,b)
  	hammingScore = 0
  	biggerList = []
  	smallerList = []

  	#detect which string is longer and split into arrays.
  	if a.length > b.length
  		biggerList = a.split("")
  		smallerList = b.split("")
  	else
  		biggerList = b.split("")
  		smallerList = a.split("")
  	end

  	#iterate over smaller array and check against larger array
  	smallerList.each_with_index do |nucleotide, i|
  		if nucleotide != biggerList[i]
  			hammingScore += 1
  		end
  	end

  	return hammingScore

 	end
end
