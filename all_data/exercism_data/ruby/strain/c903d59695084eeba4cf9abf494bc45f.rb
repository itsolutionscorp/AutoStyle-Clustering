class Array
	def keep
		#self.select{|i| yield(i)}
		x = []
		self.each{|i| x << i if yield(i)}
		x
	end

	def discard
		#self.select{|i| !yield(i)}
		x = []
		self.each{|i| x << i if !yield(i)}
		x
	end
end
