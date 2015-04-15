class Array

  def accumulate(&block)
    if self.length == 0 || self.length == 1
      return self
    else
      result = []
      self.each do |node|
        result << block.call(node)
      end
      result
    end
  end

end
