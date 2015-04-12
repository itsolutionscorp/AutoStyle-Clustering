class Hamming
  def compute(strand1, strand2)
	distance = 0
		(strand1.length).times do |i|
			distance+=1 if strand1[i]!=strand2[i]
		end
	distance
  end
end
