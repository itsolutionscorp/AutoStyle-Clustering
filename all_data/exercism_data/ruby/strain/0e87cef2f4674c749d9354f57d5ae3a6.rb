class Array
  def keep(&block)
    new_collection = []
    self.each do |item|
      new_collection << item if block.call(item)
    end
    new_collection
  end

  def discard(&block)
    self.keep {|e| !block.call(e) }
    # new_collection = []
    # self.each do |item|
    #   new_collection << item unless yield(item)
    # end
    # new_collection
  end
end
