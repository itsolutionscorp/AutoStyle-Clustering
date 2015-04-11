class Array
  def accumulate
    res = []
    self.each do |item|
      res << yield(item)
    end
    res
  end
end
