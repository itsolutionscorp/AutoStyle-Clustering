class Array
  def accumulate
    result = []
    self.each do |e|
      result << yield(e)
    end
    result
  end
end
