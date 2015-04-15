class Array
	def accumulate
		result = []
		for i in 0...self.length
			result << yield(self[i])
		end
		result
	end
end
