class Array

  def keep
    result = []
    self.each { |x| result << (x) if yield x }
    result
  end

  def discard
    result = []
    self.each { |x| result << x unless yield x }
    result
  end

end
