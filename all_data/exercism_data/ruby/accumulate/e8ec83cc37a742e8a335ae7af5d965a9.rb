class Array
  def accumulate(&block)
    if block_given?
      result = []
      each { |x| result << block.call(x) }
      result
    else
      []
    end
  end
end
