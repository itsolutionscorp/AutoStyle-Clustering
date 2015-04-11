class Array

  def accumulate(&block)
    array = []
    self.each {|array_elements| array << (block.call array_elements)}
    array
  end
end
