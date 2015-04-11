class Array
  def keep
    self.each_with_object([]) { |elem, array| array << elem if yield(elem) }
  end

  def discard
    self.each_with_object([]) { |elem, array| array << elem unless yield(elem) }
  end
end
