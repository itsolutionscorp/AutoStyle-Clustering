class Array

  def keep
    self.map { |x| x if yield(x) }.compact if block_given?
  end

  def discard
    self.map { |x| x if !yield(x) }.compact if block_given?
  end

end
