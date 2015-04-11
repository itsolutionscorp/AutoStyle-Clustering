class Array
  def accumulate
    result = []
    self.each do |elt|
      result << yield(elt)
    end
    result
  end
end
