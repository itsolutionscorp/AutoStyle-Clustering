class Array

  def keep(&block)
    collection = []
    self.each do |elem|
      collection << elem if block.call(elem)
    end
    collection
  end

  def discard(&block)
    collection = []
    self.each do |elem|
      collection << elem unless block.call(elem)
    end
    collection
  end

end
