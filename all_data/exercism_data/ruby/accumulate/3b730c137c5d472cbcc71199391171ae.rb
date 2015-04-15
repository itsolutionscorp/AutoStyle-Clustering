class Array
  def accumulate &block
    #Restriction: keep away from map
    #self.map { |i| block.call(i) }
    self.each_with_object([]) { |i, result| result << block.call(i) }
  end
end
