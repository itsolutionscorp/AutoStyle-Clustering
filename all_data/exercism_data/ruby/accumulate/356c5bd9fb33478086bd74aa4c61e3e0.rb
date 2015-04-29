class Array
  def accumulate(&block)
    self.each_with_index { |x,i| self[i] = block.call(x) }
  end
end
