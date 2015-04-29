class Array
  def keep
    result = []
    self.each do |el|
      result << el if yield(el)
    end
    result
  end

  def discard
    result = []
    self.each do |el|
      result << el unless yield(el)
    end
    result
  end
end
