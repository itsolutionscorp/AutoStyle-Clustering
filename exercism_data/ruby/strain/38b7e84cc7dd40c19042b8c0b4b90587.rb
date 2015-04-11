class Array

  def keep
    result = []

    self.each do |value| 
      result << value if yield(value)
    end
    result
  end

  def discard
    result = []

    self.each do |value|
      result << value if !yield(value)
    end
    result
  end
end
