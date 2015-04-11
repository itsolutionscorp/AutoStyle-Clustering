class Array
  def keep
    self.each_with_object([]) { |e, res| res << e if yield(e) }
  end

  def discard
    self.each_with_object([]) { |e, res| res << e unless yield(e) }
  end
end
