class Array
  def accumulate(&block)
    inject([]) { |a, e| a << block.call(e) }
  end
end
