class Array
  def accumulate
    accumulator = []
    self.each do |item|
      accumulator << yield(item)
    end
    accumulator
  end
end
