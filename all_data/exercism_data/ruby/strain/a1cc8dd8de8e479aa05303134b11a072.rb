class Array
  def keep(&predicate)
    xs = []
    each { |x| xs << x if predicate.call(x) }
    xs
  end

  def discard(&predicate)
    xs = []
    each { |x| xs << x unless predicate.call(x) }
    xs
  end
end
