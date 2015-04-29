class Array

  def accumulate(&method)
    result = []
    self.each do |item|
      result << method.call(item)
    end
    result
  end

end
