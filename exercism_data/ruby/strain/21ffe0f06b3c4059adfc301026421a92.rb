class Array
  def keep
    self.select do |element|
      element if yield element
    end
  end

  def discard
    self.select do |element|
      element unless yield element
    end
  end
end
