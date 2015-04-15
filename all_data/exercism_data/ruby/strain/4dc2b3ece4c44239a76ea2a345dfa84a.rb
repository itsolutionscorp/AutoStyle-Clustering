class Array

  def keep
    self.map { |e| e if yield e }.compact if block_given?
  end

  def discard
    self.map { |e| e unless yield e }.compact if block_given?
  end

end
