class Array
  def keep
    self.each_with_object([]) do |elem, result|
      result << elem if yield(elem)
    end
  end

  def discard
    self.each_with_object([]) do |elem, result|
      result << elem unless yield(elem)
    end
  end
end
