class Array

  def accumulate
    accumulated = []
    self.each do |e|
      accumulated << (yield e)
    end
    accumulated
  end

end
