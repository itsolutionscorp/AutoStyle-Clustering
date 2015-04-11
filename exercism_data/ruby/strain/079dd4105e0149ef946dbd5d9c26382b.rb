class Array
  def keep
    each_with_object [] { |i, a| a << i if yield i }
  end

  def discard
    keep { |i| !yield(i) }
  end
end
