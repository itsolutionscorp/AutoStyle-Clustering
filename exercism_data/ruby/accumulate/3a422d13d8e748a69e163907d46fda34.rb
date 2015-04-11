class Array
  def accumulate(&block)
    new_array = Array.new length
    i = 0
    while i < length
      new_array[i] = yield self[i]
      i += 1
    end
    new_array
  end
end
