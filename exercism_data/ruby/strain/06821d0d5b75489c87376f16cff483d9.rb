class Array
  def keep
    return enum_for(:keep) unless block_given?

    [].tap do |collection|
      each do |element|
        collection << element if yield(element)
      end
    end
  end

  def discard
    return enum_for(:discard) unless block_given?

    [].tap do |collection|
      each do |element|
        collection << element if !yield(element)
      end
    end
  end
end
