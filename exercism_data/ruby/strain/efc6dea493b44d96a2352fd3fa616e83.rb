class Array

  def keep
    result = []
    self.each do |x|
      result << (x) if yield x
    end
    result
  end

  def discard
    result = []
    self.each do |x|
      unless yield x
        result << x
      end
    end
    result
  end

end
