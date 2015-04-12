class Hamming
	def compute(ham, cheese)
		ham = ham.split("")
		cheese = cheese.split("")
		error_count = 0
		index = []
		while ham.length > cheese.length
			ham.pop
		end
		while cheese.length > ham.length
			cheese.pop
		end
		ham.each_index do |i|
			index << i
		end

		index.each do |i|
			if ham[i] != cheese[i]
				error_count += 1
			end
		end
		return error_count
			
	end
end
