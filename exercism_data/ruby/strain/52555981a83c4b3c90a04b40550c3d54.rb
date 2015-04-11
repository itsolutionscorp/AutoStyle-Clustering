class Array
  def keep
    each_with_object [] { |i, a| a << i if yield i }
  end

  def discard
    each_with_object [] { |i, a| a << i unless yield i }
  end
end
