class Array

  def keep
    each_with_object([]) do |item, object|
      object << item if yield(item)
    end
  end

  def discard
    keep {|item| !yield(item)}
  end

end
