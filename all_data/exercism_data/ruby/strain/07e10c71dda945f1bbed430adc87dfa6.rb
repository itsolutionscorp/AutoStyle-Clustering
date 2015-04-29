class Array

	def initialize
	end

	def keep
		self.select do |a|
			yield(a)
		end
	end

	def discard
		self.reject do |a|
			yield(a)
		end
	end

end
