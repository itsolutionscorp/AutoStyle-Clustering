class Array
  def accumulate(&block)
    acc = []
    each_with_index { |a, i| acc[i] = block.call(a) }
    acc
  end
end
