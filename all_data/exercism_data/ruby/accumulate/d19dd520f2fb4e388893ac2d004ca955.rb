class Array
  def accumulate(&block)
    result = []
    return result if self.length == 0

    self.each do | val |
      result << block.call(val)
    end

    result
  end
end
