class Array
  def accumulate
    self.map { |element| yield element }
  end
end
