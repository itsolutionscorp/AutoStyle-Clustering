Array.class_eval do
	def accumulate(&block)
		return [] if self.empty?
		result = Array.new
		self.each{|x|  result << block.call(x)}
		result
	end
end
