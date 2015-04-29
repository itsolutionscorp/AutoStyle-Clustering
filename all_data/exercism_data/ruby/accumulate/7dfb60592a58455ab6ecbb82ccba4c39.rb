class Array
  def accumulate(&fn)
    result = []
    self.each { |arg| result << fn.call(arg) }
    result
  end

end
