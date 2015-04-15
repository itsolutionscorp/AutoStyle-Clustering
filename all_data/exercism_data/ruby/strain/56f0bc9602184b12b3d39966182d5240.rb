class Array
  def keep &block
    self.select &block
  end

  def discard &block
    self.delete_if &block
  end
end
