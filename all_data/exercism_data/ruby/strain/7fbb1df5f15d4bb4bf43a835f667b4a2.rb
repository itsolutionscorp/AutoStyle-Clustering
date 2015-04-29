class Array

  def keep(&block)
    self.map do |element|
      if yield element
        element
      end
    end.compact
  end

  def discard(&block)
    self - self.keep(&block)
  end

end
