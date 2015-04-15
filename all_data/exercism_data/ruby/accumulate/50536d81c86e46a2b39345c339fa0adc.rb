class Array
	def accumulate(&code)
		result = []
		for x in self
			result << code.call(x)
		end
		result
	end
end
