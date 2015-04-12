class Hamming
	def compute(first, second)
		buf = 0
		first.split('').each_index do |i|
			if second[i]!=first[i]
				buf+=1
			end
		end
		buf
	end
end
