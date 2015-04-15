class Array
  def keep
    self.each_with_object([]) {|e, kept| kept << e if yield e}
  end

  def discard
    self.each_with_object([]) {|e, discarded| discarded << e unless yield e}
  end
end
