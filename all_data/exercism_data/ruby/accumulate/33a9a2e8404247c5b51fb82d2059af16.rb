class Array

  def accumulate(&block)
    new_array = []
    each_with_index do |value, i|
      new_array << block.call(value)
    end
    return new_array
  end

end
