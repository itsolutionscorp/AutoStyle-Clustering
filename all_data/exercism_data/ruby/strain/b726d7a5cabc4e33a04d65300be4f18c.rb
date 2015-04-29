module MyFilters

  def keep
    self.each_with_object([]) { |e, memo| memo << e if yield e  }
  end

  def discard
    self.each_with_object([]) { |e, memo| memo << e unless yield e  }
  end
end

Array.send :include, MyFilters
