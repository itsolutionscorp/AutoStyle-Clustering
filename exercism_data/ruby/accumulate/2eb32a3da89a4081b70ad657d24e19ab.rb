class Array
	def initialize
	end
	def accumulate
		self.map do |a|
			yield(a)
		end
	end
end
