class Array
  def keep(&block)
    result = []
    for index in 0..(self.size - 1)
      result << self[index] if yield self[index]
    end
    result
  end

  def discard(&block)
    result = []
    for index in 0..(self.size - 1)
      result << self[index] unless yield self[index]
    end
    result
  end
end
