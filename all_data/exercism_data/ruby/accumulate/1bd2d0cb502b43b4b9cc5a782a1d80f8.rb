class Array
  def accumulate
    [].tap do |result|
      self.each do |elem|
        result << yield(elem)
      end
    end
  end
end
