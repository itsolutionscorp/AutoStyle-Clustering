class Array
  def accumulate
    self.map {|e| yield e}
  end
end
