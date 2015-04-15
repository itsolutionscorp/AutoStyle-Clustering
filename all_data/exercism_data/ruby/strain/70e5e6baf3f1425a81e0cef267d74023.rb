module MyFilters

  def keep &pred
    self.each_with_object([]) { |e, memo| memo << e if pred.call e  }
  end

  def discard &pred
    self.each_with_object([]) { |e, memo| memo << e if !pred.call e  }
  end
end

Array.send :include, MyFilters
