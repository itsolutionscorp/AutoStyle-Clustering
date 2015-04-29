class Array
  def accumulate &block
    self.collect &block
  end
end
