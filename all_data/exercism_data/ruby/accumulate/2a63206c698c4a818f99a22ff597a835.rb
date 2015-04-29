class Array
  def accumulate
    each.with_object([]) { |item, res| res << yield(item) }
  end
end
