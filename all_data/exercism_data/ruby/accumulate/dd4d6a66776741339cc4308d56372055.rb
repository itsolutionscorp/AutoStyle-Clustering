class Array
	def accumulate
		self.each_with_object([]) {|e,a| a << yield(e)}
	end
end
