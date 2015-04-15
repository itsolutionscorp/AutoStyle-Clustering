class Array
  def accumulate
    size.times do |i|
      self[i] = yield self[i]
    end
    self
  end
end
