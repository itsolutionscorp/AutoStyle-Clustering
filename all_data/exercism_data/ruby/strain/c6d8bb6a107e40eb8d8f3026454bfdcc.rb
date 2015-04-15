class Array

  def keep
  	self.select do |x|
  		yield x
  	end
  end

  def discard
    self.delete_if do |x|
      yield x
    end
  end

end
