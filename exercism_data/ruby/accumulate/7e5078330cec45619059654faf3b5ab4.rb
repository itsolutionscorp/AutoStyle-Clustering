class Array

  def accumulate(&block)
    self.each_with_index do |value, i|
      self[i] = block.call(value)
    end
  end

end
