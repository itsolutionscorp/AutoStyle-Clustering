class Hamming
	def compute(a, b)
		i=0
		res = 0
		until a[i].nil? || b[i].nil?
		res += 1 if a[i] != b[i]
		i+=1
		end
		res
	end
end
