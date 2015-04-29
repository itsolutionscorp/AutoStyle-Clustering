class Array
  def accumulate(&block)
    arr = self
    result = []
    for item in arr
      result << yield(item)
    end
    result
  end
end
