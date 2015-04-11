class Array
  def accumulate(&block)
    result = []
    each { |element| result << block.call(element) }

    result
  end
end
