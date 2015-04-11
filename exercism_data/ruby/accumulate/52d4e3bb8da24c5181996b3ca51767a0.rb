class Array
  def accumulate
    self.each_with_object([]) do |i, arr|
      arr << yield(i)
    end
  end

end
