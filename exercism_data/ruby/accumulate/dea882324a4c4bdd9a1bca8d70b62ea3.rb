class Array
  def accumulate
    return_array = []
    size.times do |i|
      new_val = yield self[i]
      return_array << new_val
    end
    return_array
  end

  def accumulate!
    size.times do |i|
      self[i] = yield self[i]
    end
    self
  end
end
