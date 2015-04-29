class Array
	def keep
		self.each_with_object([]) { |elem, arr| arr << elem if yield(elem) }
	end

	def discard
		self.each_with_object([]) { |elem, arr| arr << elem if not yield(elem) }
	end
end
