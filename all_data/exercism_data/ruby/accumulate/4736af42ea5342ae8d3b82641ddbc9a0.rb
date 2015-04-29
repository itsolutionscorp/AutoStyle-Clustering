class Array
  def accumulate
    each_with_object([]) { |x, result| result << yield(x) }
  end
end
