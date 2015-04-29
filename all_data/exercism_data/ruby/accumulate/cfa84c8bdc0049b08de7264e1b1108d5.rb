class Array
  def accumulate(&operation)
    result = []
    self.each { |element|
       result << operation.call(element)
    }
    result
  end
end
