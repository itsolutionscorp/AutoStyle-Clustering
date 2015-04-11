class Array

  def keep(&block)
    self.map do |element|
      if yield element
        element
      end
    end.compact
  end

  def discard(&block)
    self.map do |element|
      unless yield element
        element
      end
    end.compact
  end

end
