class Array
  def accumulate
    result = []
    self.each { |s| result << yield(s) } 
    result
  end
end
