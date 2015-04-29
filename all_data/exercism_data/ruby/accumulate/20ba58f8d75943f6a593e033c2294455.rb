class Array
  def accumulate
    accumulated = []

    self.each do |item|
      accumulated << yield(item)
    end
    accumulated
  end
end
