class Array
  def accumulate(&block)
    Array.new(self.size).tap do |new_arr|
      self.each_with_index do |el, index|
        new_arr[index] = block.call(el)
      end
    end
  end
end
