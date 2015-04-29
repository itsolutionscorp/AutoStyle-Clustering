class Array
  def accumulate
    each_with_object([]) { |element, ary| ary << yield(element) }
  end
end
