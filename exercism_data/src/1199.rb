class Hamming
	def Hamming.compute(strand1,strand2) 
		minlength = (strand1.length >= strand2.length) ? strand2.length : strand1.length
		distance=0
		
		for i in 0..minlength - 1
			distance += 1 unless strand1[i].eql? strand2[i]	
		end 

		distance
 	end
end
