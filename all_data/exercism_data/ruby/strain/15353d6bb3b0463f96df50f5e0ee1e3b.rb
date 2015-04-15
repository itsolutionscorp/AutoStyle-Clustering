class Array
  def keep(&block)
    keep_if_yield_returns(true, &block) 
  end

  def discard(&block)
    keep_if_yield_returns(false, &block)
  end

  private

  def keep_if_yield_returns(keep_value)
    index = 0
    len = length
    filtered = []
    while index < len
      item = self[index]
      filtered.push item if (yield item) == keep_value
      index += 1
    end
    filtered
  end
end
