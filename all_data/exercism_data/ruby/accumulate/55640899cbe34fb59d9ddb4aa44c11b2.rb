class Array
  def accumulate
    new = []
    self.each_with_index do |item, index|
      new[index] =  yield item
    end
    new
  end
end
