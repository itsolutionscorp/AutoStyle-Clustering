class Array
  def keep
    self.map { |element| element if yield element }.compact
  end

  def discard(&test)
    self.map { |element| element unless test.call(element) }.compact
  end
end
