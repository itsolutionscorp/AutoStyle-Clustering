class Array
  def keep
    each_with_object([]) { |i, res| res << i if yield(i) }
  end

  def discard
    each_with_object([]) { |i, res| res << i if !yield(i) }
  end
end
