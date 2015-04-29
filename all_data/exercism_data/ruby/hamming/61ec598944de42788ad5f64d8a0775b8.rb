class Hamming
	#compute is implemented as class-method
	class << self
		def compute(strandA, strandB)
			distance = 0

			#get length of smallest strand
			length = strandA.length <= strandB.length ? strandA.length : strandB.length

			#calculate distance
			0.upto(length-1) do |i|
				distance += 1 unless strandA[i] == strandB[i]
			end
			
			distance
		end
	end
end
