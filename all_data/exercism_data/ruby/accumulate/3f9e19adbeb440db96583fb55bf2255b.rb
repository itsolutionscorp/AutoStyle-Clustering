class Array
  def accumulate(&block)
    return self if self.empty?
    [block.call(self.shift)] + self.accumulate(&block)
  end
end
