class Array
  def keep(&block)
    reduce([]) { |total, obj| block.call(obj) ? total << obj : total }
  end

  def discard(&block)
    self - keep(&block)
  end
end
