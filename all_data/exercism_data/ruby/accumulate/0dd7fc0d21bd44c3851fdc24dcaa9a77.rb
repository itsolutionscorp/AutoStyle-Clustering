class Array

  def accumulate
    result = []
    for item in self
      result << yield(item)
    end
    return result
  end

end
