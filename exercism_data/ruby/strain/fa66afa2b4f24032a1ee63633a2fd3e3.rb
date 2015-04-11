class Array
  def keep
    each_with_object([]) do |el, result|
      result << el if yield(el)
    end
  end

  def discard
    keep{ |el| !yield(el) }
  end
end
