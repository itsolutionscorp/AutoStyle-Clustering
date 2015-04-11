class Array

  def keep
    result = []
    for item in self
      result << item if yield(item)
    end
    result
  end

  def discard
    result = []
    for item in self
      result << item unless yield(item)
    end
    result
  end

end
