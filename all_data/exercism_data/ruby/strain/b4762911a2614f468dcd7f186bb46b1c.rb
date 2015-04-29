class Array

  def keep(&block)
    self.map do |element|
      element if yield element
    end.compact
  end

  def discard(&block)
    self - keep(&block)
  end

end
