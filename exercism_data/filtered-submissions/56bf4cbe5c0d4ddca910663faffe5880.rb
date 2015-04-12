class Hamming
  def compute(strand1, strand2)
    distance = 0
    for i in 0...[strand1.length, strand2.length].min
	 if strand1[i] != strand2[i] then distance +=1 end 
    end 
    return distance
  end
end
