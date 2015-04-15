def compute(strand1, strand2)
		strand1=strand1.split("")
		strand2=strand2.split("")

		distance=0
		count=0

		strand1.each do |letter|
			if letter==strand2[count]
				distance+=0
			elsif strand2[count]==nil
				distance+=0
			else
				distance+=1
			end
			count+=1
		end

		return distance
	end