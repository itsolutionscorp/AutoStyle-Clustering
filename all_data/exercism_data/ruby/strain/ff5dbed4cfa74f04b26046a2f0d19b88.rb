class Array
  def keep(&test)
    self.map { |num| num if test.call(num) }.compact
  end

  def discard(&test)
    self.map { |num| num unless test.call(num) }.compact
  end
end
