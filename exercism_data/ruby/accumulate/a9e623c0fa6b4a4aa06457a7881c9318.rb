class Array
  def accumulate
    return self if self.empty?
    for i in (0..self.index(self.last))
      self[i] = (yield self[i])
    end
    self
  end
end
