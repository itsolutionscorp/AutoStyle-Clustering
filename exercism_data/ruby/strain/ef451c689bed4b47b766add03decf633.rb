class Array

  def keep(&block)
    self.select do |element|
      yield element
    end
  end

  def discard(&block)
    self.reject do |element|
      yield element
    end
  end

end
