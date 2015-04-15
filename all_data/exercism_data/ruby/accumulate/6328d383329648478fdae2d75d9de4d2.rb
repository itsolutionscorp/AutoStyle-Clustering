class Array
	def accumulate(&block)
		[] if self.empty?
		output = []
		self.collect &block 
	end
end
