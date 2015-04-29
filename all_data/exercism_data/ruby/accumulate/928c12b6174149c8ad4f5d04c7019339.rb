class Array
  def accumulate
    self.each_with_object([]) do |item, accumulator|
      accumulator << yield(item)
    end
  end
end
