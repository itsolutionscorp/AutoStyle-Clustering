class Array
  def accumulate(&block)
    result = []
    for index in (0..size)
      result << block.call(get(index))
    end
    result
  end
end
