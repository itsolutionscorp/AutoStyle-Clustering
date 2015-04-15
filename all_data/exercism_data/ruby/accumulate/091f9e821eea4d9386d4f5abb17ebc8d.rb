class Array
	def accumulate(&block)
		for i in 1..(self.length)
      self[i-1] = block.call(self[i-1])
    end
    self
	end
end
