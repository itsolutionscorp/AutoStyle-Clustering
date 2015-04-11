class Array
  def accumulate(&block)
    result = []
    self.each do |element|
      result << block.call(element)
    end
    result
  end
end
