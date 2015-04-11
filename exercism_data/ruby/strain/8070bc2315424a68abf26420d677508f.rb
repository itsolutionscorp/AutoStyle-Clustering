class Array
  def keep
    self.flat_map {|e| yield(e) ? [e] : []}
  end

  def discard
    self.flat_map {|e| yield(e) ? [] : [e]}
  end
end
