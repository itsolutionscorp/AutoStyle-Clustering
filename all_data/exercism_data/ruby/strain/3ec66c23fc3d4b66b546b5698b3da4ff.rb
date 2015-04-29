class Array

  def keep(&block)
    self.select &block
  end

  def discard(&block)
    self.reject &block
  end
end
