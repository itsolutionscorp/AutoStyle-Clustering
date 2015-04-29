class Array
  def keep
    result = []

    each do |element|
      result << element if yield(element)
    end

    result
  end

  def discard(&block)
    self - keep(&block)
  end
end
