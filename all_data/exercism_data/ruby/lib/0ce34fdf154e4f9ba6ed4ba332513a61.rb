class Array

  def keep(&block)
    self.select do |elem|
      block.call(elem)
    end
  end

  def discard(&block)
    self.reject do |elem|
      block.call(elem)
    end
  end

end
