class Array
  def keep(&block)
    select { |x| block.call(x) }
  end

  def discard(&block)
    self - keep(&block)
  end
end
