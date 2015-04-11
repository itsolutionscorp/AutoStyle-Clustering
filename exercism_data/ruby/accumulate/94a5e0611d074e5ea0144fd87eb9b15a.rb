class Array
  def accumulate
    self.inject([]) do |array, block|
      array << yield(block)
    end
  end
end
