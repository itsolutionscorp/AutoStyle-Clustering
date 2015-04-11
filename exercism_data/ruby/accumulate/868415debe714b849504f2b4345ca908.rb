class Array
	def accumulate
		a = []
		self.each{|i| a << (yield i)}
		a
	end
end
