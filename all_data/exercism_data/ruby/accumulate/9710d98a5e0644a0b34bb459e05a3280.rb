class Array

	def accumulate
		out = Array.new
			self.each do |x|
				out << yield(x)
			end
		out
	end

end
