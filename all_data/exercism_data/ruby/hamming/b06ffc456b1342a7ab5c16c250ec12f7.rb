class Hamming
	def self.compute string1, string2
		testlength = [string1.length, string2.length].min
		hammingdistance = 0
		for i in 0..(testlength-1)
			if string1[i] != string2[i]
				hammingdistance = hammingdistance + 1
			end
		end
		return hammingdistance
	end
end
