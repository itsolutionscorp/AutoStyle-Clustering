class Array
  def accumulate
    each_with_object [] { |element, result| result << yield(element) }
  end
end
