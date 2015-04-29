class Array
  def accumulate
    self.reduce([]){|result, n| result << yield(n)}
  end
end
