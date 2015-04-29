class Array
  def accumulate
    self.reduce([]) { |result, e| result << yield(e) }
  end
end
