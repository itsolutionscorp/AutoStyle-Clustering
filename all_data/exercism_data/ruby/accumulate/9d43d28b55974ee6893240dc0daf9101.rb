class Array
  def accumulate(&operation)
    return enum_for(:accumulate) unless block_given?

    [].tap do |new_collection|
      each do |element|
        new_collection << yield(element)
      end
    end
  end
end
