class Array
  def keep(&block)
    result = []
    for index in 0..(self.size - 1)
      result << self[index] if block.call(self[index])
    end
    result
  end

  def discard(&block)
    result = []
    for index in 0..(self.size - 1)
      result << self[index] unless block.call(self[index])
    end
    result
  end
end
