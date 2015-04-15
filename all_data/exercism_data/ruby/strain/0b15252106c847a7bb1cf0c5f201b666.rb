class Array
  
  def keep
    self.select { |input|  yield(input) }
  end

  def discard
    self.select { |input| !yield(input) }
  end
end
