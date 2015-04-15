class Array
  def accumulate(&block)
    result = Array.new(length)
    for i in 0...length do
      result[i] = block.call self[i]
    end
    result
  end
end
