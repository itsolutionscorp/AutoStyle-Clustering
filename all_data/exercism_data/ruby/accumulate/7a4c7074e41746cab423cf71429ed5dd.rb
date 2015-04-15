class Array
  def accumulate
    result = []
    self.each { |e| result << yield(e) }
    result
  end
end
