module MyFilters

  def keep
    each_with_object([]) { |e, memo| memo << e if yield e  }
  end

  def discard &block
    self - (keep &block)
  end
end

Array.send :include, MyFilters
