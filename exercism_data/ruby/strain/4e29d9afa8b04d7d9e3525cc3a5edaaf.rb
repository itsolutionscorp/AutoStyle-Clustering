class Array
  def keep
    each_with_object([]) { |e, a| a << e if yield e }
  end

  def discard
    self - keep { |e| yield e }
  end
end
