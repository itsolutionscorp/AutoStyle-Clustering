class Array
  def accumulate(&block)
    return [] unless head = self.shift
    [block.call(head), *accumulate(&block)]
  end
end
