class Array
  def keep
    self.each_with_object([]) do |elem, result|
      result << elem if yield(elem)
    end
  end

  def discard
    keep { |elem| !yield(elem) }
  end
end
