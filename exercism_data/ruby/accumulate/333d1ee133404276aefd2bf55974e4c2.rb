class Array
  def accumulate
    return self if empty?
    for i in (0..index(last))
      self[i] = yield self[i]
    end
    self
  end
end
