class Array
  def accumulate(&b)
    result = []
    self.each { |e| result << b.call(e) }
    result
  end
end
