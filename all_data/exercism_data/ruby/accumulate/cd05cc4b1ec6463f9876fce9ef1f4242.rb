class Array

  def accumulate(&block)
    self.map { |e| yield e }
  end

end
