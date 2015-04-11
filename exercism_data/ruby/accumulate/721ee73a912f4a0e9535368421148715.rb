class Array
  def accumulate
    self.each_with_object([]) { |element, array| array << yield(element) }
  end
end
