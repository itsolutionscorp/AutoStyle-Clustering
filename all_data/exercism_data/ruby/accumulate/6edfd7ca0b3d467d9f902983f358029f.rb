class Array
  def accumulate(&block)
    for i in 0..(self.length-1)
      self[i] = block.call(self[i])
      end
    self
	end
end
