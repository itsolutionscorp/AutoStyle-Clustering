class Array
  def keep(&test)
    results = []
    self.each { |num| results << num if test.call(num) }
    results
  end

  def discard(&test)
    results = []
    self.each { |num| results << num unless test.call(num) }
    results
  end
end
