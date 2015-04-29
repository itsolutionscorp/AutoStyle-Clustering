class Array
  def accumulate
    new = []
    self.each { |i| new << (yield i) }
    new
  end
end
