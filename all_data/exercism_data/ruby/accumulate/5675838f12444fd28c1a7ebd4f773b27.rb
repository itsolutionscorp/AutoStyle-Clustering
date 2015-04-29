module Accumulation
	def accumulate
		result = []
		for i in 0...self.size
			result << yield(self[i])
		end
		result
	end
end

class Array
	prepend Accumulation
end
