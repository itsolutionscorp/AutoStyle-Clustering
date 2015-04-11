class Array
  def accumulate(&block)
    [].tap { |a| each { |v| a << block.call(v) } }
  end
end
