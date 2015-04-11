class Array
	def accumulate
		results = Array.new
		self.each do |element|
			results.push(yield(element))
		end
		results
	end
end
