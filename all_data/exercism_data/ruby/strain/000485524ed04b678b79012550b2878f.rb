class Array
  def keep &block
    self.each_with_object([]) do |i, result|
      result << i if yield i
    end
  end

  def discard &block
    self.each_with_object([]) do |i, result|
      result << i unless yield i
    end
  end
end
