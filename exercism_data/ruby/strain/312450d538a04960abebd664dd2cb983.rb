class Array
  def keep(&test)
    array = []
    self.each { |num| array << num if test.call(num) }
    array
  end

  def discard(&test)
    array = []
    self.each { |num| array << num unless test.call(num) }
    array
  end
end
