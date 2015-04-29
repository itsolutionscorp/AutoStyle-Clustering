class Array
	
	def keep
		result = []
		
		i = 0
		while self[i]
			result << self[i] if yield self[i]
			i += 1
		end

		result
	end

	def discard
		result = []

		i = 0
		while self[i]
			result << self[i] if !(yield self[i])
			i += 1
		end

		result
	end

end
