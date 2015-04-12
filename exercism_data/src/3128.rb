class Hamming
	def compute(v1,v2)
		if v1.length = v2.length
			return 0
		end
		if v1.length != v2.length
			return 1
		end
		i=0
		acc=0
		until v1.length != null
			if v1[i] != v2[i]
				acc +=1
				i+=1
			end
			return acc
		end
	end

end
