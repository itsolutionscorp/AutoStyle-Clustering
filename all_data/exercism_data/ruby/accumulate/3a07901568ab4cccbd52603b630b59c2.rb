class Array
  def accumulate(&operation)
    [].tap do |new_collection|
      each do |element|
        new_collection << operation.yield(element)
      end
    end
  end
end
