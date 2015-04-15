class Array
  def accumulate(&operation)
    [].tap do |new_collection|
      each do |element|
        new_collection << yield(element)
      end
    end
  end
end
