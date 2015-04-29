class Array
 
  def accumulate 
    ndx = 0

    while ndx < (self.count ) do
      self[ndx] = yield self[ndx]
      ndx += 1
    end
    return self
  end
end
