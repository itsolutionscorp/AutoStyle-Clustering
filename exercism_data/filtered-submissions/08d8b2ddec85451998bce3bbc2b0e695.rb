class Hamming
	def Hamming.compute(a,b)
		len = a.length <= b.length ? a.length : b.length
		for i in 0...len
			if a[i] == b[i]
				len-=1
			end
		end
		return len
	end
end
puts Hamming.compute('A','G')
