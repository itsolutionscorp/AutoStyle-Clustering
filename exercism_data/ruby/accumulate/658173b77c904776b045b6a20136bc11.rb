class Array
  def accumulate(&block)
    self.reduce([]) { |array, e| array.push block.call(e) }
  end
end
