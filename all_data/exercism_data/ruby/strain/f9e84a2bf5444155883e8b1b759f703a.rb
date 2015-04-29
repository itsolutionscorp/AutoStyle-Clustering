class Array
  def keep
    each_with_object([]) { |x, out| out << x if yield(x) }
  end

  def discard
    keep { |x| !yield(x) }
  end
end
