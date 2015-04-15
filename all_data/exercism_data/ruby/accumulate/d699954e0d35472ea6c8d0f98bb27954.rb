class Array

  def accumulate
    result = []
    
    self.each { |value| result << yield(value) }

    result
  end
end
