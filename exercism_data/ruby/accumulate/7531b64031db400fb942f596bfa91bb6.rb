class Array
  def accumulate(&block)
    self.each_with_object([]) do |element, result|
      result << block.call(element)
    end
  end
end
