class Array

  def accumulate(&block)
    collection = []
    self.each do |item|
      collection << block.call(item)
    end
    collection
  end

end
