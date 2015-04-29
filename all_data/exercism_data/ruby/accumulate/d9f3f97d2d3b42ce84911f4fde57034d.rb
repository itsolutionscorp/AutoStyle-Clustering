class Array
  def accumulate(&operation)
    new_collection = []
    self.each do |element|
      new_collection << operation.call(element)
    end
    new_collection
  end
end
