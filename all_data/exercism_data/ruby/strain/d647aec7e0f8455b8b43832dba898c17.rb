class Array
  def keep
    each_with_object([]) do |element, result|
      result << element if yield(element)
    end
  end

  def discard(&block)
    self - keep(&block)
  end
end
