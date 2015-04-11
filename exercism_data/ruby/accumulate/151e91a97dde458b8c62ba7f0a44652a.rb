class Array
  def accumulate
    result = []
    self.each do |el|
      result << yield(el)
    end
    result
  end
end
