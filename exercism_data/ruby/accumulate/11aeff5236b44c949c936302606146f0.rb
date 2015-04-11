class Array
  def accumulate(&block)
    array = []
    self.each_with_index { |element, index| array << block.call(element) }
    array
  end
end
