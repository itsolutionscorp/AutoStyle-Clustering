class Array
  def accumulate(&block)
    arr = []
    self.each do |element|
      arr << yield(element)
    end
    return arr
  end
end
