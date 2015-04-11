class Array
  def accumulate(&block)
    result_array = []
    self.each do |item|
      result = block.call(item)
      result_array << result
    end
    result_array
  end
end
