class Array
  def keep(&block)
    result = []
    for i in 0...length do
      result << self[i] if block.call self[i]
    end
    result
  end

  def discard(&block)
    keep { |x| !block.call(x) }
  end
end
