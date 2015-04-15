class Array
  def accumulate &operation
    self.each_index { |index| self[index] = operation.call(self[index]) }
  end
end
