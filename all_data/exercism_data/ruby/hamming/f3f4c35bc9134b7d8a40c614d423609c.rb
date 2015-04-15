#Henok Addis Code
class Hamming
	def self.compute(a, b)
		counter = 0
		hammingDistance = 0
		if a.eql? b #if the two strands are equal
			0
		else
			a=a.split('')
			b=b.split('')
			while counter < a.length && counter < b.length
				if a[counter] != b[counter]
					hammingDistance +=1 #increase hamming distance
				end
				counter +=1
			end
			hammingDistance
		end
	end
end
