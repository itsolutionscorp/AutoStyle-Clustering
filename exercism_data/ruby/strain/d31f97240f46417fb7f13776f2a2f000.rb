class Array

  def keep
    self.select {|x| yield x}
  end

  def discard
    self.select {|x| !yield x}
  end

end
