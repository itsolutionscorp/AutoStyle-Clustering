class Hamming
	def compute(arg1,arg2)
		line1 = arg1.chars.to_a
		line2 = arg2.chars.to_a
		comp = 0
		line1.each_index do |index|
		 comp += 1 if line1[index] != line2[index] 
		end	
		return comp
	end
end
