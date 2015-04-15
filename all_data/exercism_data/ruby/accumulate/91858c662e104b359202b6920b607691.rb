class Array
  def accumulate(&block)
    result = []
    self.each do |sel|
      result << block.call(sel)
    end
    result
  end

end
