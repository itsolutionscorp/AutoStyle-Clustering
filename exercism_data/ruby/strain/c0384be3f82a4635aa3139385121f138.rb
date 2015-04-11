class Array
  def keep(&test)
    self.map { |element| element if test.call(element) }.compact
  end

  def discard(&test)
    self.map { |element| element unless test.call(element) }.compact
  end
end
