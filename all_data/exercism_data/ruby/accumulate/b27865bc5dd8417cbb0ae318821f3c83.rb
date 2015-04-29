class Array
  def accumulate
    return_array = Array.new()
    for i in 0...self.length
      return_array[i] = yield self[i]
      end
    return_array
	end
end
