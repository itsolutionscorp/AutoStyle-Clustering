class Array
  def accumulate
    result = []
    self.each { |element| result << yield(element) }
    result
  end
end
