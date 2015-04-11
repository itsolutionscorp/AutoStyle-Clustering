class Array
  def keep(&block)
    output = []
    self.each { |element| output << element if yield element }
    output
  end

  def discard(&block)
    output = []
    self.each { |element| output << element unless yield element }
    output
  end
end
