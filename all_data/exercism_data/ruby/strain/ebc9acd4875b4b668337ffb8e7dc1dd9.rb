class Array
	def keep(&block)
		result = []
		self.each{ |x| result << x if block.call(x)}
		result	
	end

	def discard(&block)
		result = []
		self.each{ |x| result << x unless block.call(x) }
		result
	end
end
