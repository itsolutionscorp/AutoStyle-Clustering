class Array
  def keep(&block)
    self.each_with_object([]) do |element, result|
      result << element if yield(element)
    end
  end

  def discard(&block)
    self.each_with_object([]) do |element, result|
      result << element unless yield(element)
    end
  end
end