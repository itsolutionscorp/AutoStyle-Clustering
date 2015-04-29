class Array

  def keep
    result = []
    self.each do |x|
      if yield x
        result << (x)
      end
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
