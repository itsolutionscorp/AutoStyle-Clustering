class Array

  def keep
    self.select do |element|
      yield element
    end
  end

  def discard(&block)
    self - keep(&block)
  end

end
