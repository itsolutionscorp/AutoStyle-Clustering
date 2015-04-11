class Array
	
	def accumulate
		result = []
		i = 0
		while self[i]
			result << (yield self[i])
			i += 1
		end
		result
	end

end
