class Array
	def keep
		out = []
		self.each { |x| out << x if yield(x)}
		out
	end

	def discard
		out = []
		self.each { |x| out << x if !yield(x)}
		out
	end
end
