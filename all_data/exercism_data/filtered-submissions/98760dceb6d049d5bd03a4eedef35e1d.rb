class Hamming

  def Hamming.compute (dna1, dna2)
  	len = [dna1.length, dna2.length].min
  	count = 0
  	for i in 0...len do
  	  if dna1[i] != dna2[i]
  	  	count += 1
  	  end
  	end
  	return count
  end

end
