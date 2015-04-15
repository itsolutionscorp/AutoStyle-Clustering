class Array
  def accumulate(&block)
    output = []
    self.each { |item| output << (yield item) }
    output
  end
end
