class Array
  def accumulate_old(&b)
    self.each_with_object([]) { |i, result| result << b.call(i) }
  end

  def accumulate
    Array.new(length) { |i| yield self[i] }
  end
end
