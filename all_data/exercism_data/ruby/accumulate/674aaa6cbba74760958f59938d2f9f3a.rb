class Array
  def accumulate
    result = []
    self.each { |arg| result << yield(arg) }
    result
  end

end
