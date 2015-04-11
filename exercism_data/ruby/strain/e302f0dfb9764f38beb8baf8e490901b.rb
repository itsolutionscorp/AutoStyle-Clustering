class Array
  # Since #filter is not allowed we use #each_with_object instead

  def keep
    each_with_object([]) do |elem, collection|
      collection << elem if yield(elem)
    end
  end

  def discard
    each_with_object([]) do |elem, collection|
      collection << elem unless yield(elem)
    end
  end
end
